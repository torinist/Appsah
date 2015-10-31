package backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.ContactService;
import logic.LoginService;
import logic.TopMenuService;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;

import util.LoginVerifier;
import bean.ContactBean;
import bean.LoginUserBean;
import bean.TopMenuBean;

@Named
@ViewScoped
public class IndexBackingBean implements Serializable {

	private List<ContactBean> conb;

	private MenuModel menuModel;

	@Inject
	TopMenuService topMenuService;

	@Inject
	private ContactService contactService;

	@Inject
	LoginVerifier loginVerifier;

	@Inject
	LoginUserBean loginUser;

	@Inject
	Logger logger;

	@Inject
	LoginService loginService;

	// ログインしているか否かの判定変数
	private Boolean loginVerify;

	@PostConstruct
	public void init() {
		conb = contactService.contactListCreate();

		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();
		tmList = topMenuService.topMenuCreate();

		menuModel = new DefaultMenuModel();

		for (TopMenuBean tm : tmList) {
			DefaultSubMenu dsm = new DefaultSubMenu(tm.getParent());
			// List<String> children = new ArrayList<String>();
			for (String child : tm.getChildren()) {
				// 親（dsm）の要素としてitemを追加
				DefaultMenuItem item = new DefaultMenuItem(child);
				dsm.addElement(item);
			}
			// 親と子のセット（dsm）をmenuModelに追加
			menuModel.addElement(dsm);
		}

		// ログイン判定
		loginVerify = loginVerifier.loginVerify();

	}

	public String logout() {
		logger.info("ログアウトします。");
		loginUser.logout();
		return "index?faces-redirect=true";
	}

	public void updateTwitter() {
		conb = contactService.contactListCreate();
	}

	public List<ContactBean> getConb() {
		return conb;
	}

	public MenuModel getMenuModel() {
		return menuModel;
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

	// contactを追加するメソッド
	public void viewContactEdit() {
		RequestContext.getCurrentInstance().openDialog("contactEdit");
	}

}
