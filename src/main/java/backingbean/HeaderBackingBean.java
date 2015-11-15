package backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.LoginService;

import org.slf4j.Logger;

import util.LoginVerifier;
import bean.LoginUserBean;

@Named(value = "headerBackingBean")
@RequestScoped
public class HeaderBackingBean {

	@Inject
	LoginVerifier loginVerifier;

	@Inject
	LoginUserBean loginUser;

	@Inject
	LoginService loginService;

	@Inject
	private Logger logger;

	Boolean loginVerify;

	@PostConstruct
	public void init() {
		// ログイン判定
		loginVerify = loginVerifier.loginVerify();
	}

	public String logout() {
		logger.info("ログアウトします。");
		loginUser.logout();
		return "index?faces-redirect=true";
	}

	public Boolean getLoginVerify() {
		return loginVerify;
	}

	public void setLoginVerify(Boolean loginVerify) {
		this.loginVerify = loginVerify;
	}

	public LoginUserBean getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUserBean loginUser) {
		this.loginUser = loginUser;
	}

}
