package backingbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.TopMenuService;
import util.LoginVerifier;
import bean.LoginUserBean;
import bean.MenuBean;

@Named
@ViewScoped
public class MenuContentsDetailBackingBean implements Serializable {

	@Inject
	MenuBean menu;

	@Inject
	TopMenuService topMenuService;

	@Inject
	LoginVerifier loginVerifier;

	@Inject
	LoginUserBean loginUser;

	// ログインしているか否かの判定変数
	private Boolean loginVerify;

	Boolean editable = false;

	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();

		// menuIdがnullでなければ詳細表示、編集
		// menuIdがnullであれば新規登録

		String menuId = (String) flash.get("menuId");

		try {
			menu = topMenuService.findMenu(menuId);

			// ログイン判定
			loginVerify = loginVerifier.loginVerify();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"選択された項目が見つかりませんでした", e.getMessage()));
		}
	}

	public void loginCheck() {
		if (loginVerify) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "ノーログイン", "参照のみできます"));
		}
	}

	// 決定ボタン押下
	public String edit() throws Exception {
		menu.setLastupMemberId(loginUser.getUserId());
		menu.setLastupMemberName(loginUser.getUserName());
		topMenuService.editMenu(menu);
		editable = false;

		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put("menuId", menu.getId());
		flash.put("parentId", menu.getParentId());

		return "menuContentsDetail?faces-redirect=true";
	}

	public MenuBean getMenu() {
		return menu;
	}

	public void setMenu(MenuBean menu) {
		this.menu = menu;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public LoginUserBean getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUserBean loginUser) {
		this.loginUser = loginUser;
	}

	public Boolean getLoginVerify() {
		return loginVerify;
	}

	public void setLoginVerify(Boolean loginVerify) {
		this.loginVerify = loginVerify;
	}

}
