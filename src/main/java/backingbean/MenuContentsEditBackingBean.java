package backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import logic.TopMenuService;
import util.LoginVerifier;
import bean.MenuBean;

@Named
@RequestScoped
public class MenuContentsEditBackingBean {

	@Inject
	TopMenuService topMenuService;

	@Inject
	MenuBean parentTmb;

	@Inject
	LoginVerifier loginVerifier;

	// 参照、更新のフラグ
	Boolean editable = false;

	// 新規作成のフラグ
	Boolean additional = false;

	// デフォルトはログアウト状態（true）
	Boolean loginVerify = true;

	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		String menuId = (String) flash.get("menuId");
		String parentId = (String) flash.get("parentId");

		// 初回であればinitの中でなくていいかなあ
		// 初回はmenuIDの有無とparentIDの有無のみ、初回は更新はない

		if(menuId!=null) {
			// menuIdがある場合
			// 参照
			editable = false;
			additional = false;

		} else if(parentId!=null) {
			// parentIdがある場合
			// 新規作成
			// loginしているか否かの判定
			loginCheck();
			editable = true;
			additional = true;

		} else {
			// どちらもない場合
			// TODO: エラーを出す
		}
	}

	public void loginCheck() {
		loginVerify = loginVerifier.loginVerify();
	}

	public MenuBean getParentTmb() {
		return parentTmb;
	}

	public void setParentTmb(MenuBean parentTmb) {
		this.parentTmb = parentTmb;
	}

}
