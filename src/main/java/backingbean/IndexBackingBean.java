package backingbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.ContactService;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;

import bean.ContactBean;

@Named
@ViewScoped
public class IndexBackingBean implements Serializable {

	private List<ContactBean> conb;

	@Inject
	private ContactService contactService;

//	@Inject
//	LoginVerifier loginVerifier;

//	@Inject
//	LoginUserBean loginUser;

	@Inject
	Logger logger;

//	@Inject
//	LoginService loginService;

//	// ログインしているか否かの判定変数
//	private Boolean loginVerify;

	@PostConstruct
	public void init() {
		conb = contactService.contactListCreate();

		// ログイン判定
//		loginVerify = loginVerifier.loginVerify();

	}

//	public String logout() {
//		logger.info("ログアウトします。");
//		loginUser.logout();
//		return "index?faces-redirect=true";
//	}

	public void updateTwitter() {
		conb = contactService.contactListCreate();
	}

	public List<ContactBean> getConb() {
		return conb;
	}

//	public LoginUserBean getLoginUser() {
//		return loginUser;
//	}
//
//	public void setLoginUser(LoginUserBean loginUser) {
//		this.loginUser = loginUser;
//	}
//
//	public Boolean getLoginVerify() {
//		return loginVerify;
//	}
//
//	public void setLoginVerify(Boolean loginVerify) {
//		this.loginVerify = loginVerify;
//	}

	// contactを追加するメソッド
	public void viewContactEdit() {
		RequestContext.getCurrentInstance().openDialog("contactEdit");
	}

}
