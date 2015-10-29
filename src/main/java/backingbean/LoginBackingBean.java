package backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import logic.LoginService;

import org.slf4j.Logger;

import util.Constant;
import util.LoginVerifier;

@Named
@RequestScoped
public class LoginBackingBean {

	private String empId;

	@Inject
	LoginService loginService;

	@Inject
	LoginVerifier loginVerifier;

	// ログインしているか否かの判定変数
	private Boolean loginVerify;

	@Inject
	transient Logger logger;

	@PostConstruct
	public void init() {
		loginVerify = loginVerifier.loginVerify();
	}

	public String loginCheck() {
		if (loginVerifier.loginVerify()) {
			return null;

		} else {
			logger.info("ログイン済みのためindex.xhtmlに遷移します。");
			return "index.xhtml";
		}
	}

	public String login() {
		int result = loginService.login(empId);
		if (result == Constant.SUCCESS) {
			logger.info("ログイン成功：" + empId);
			return "index.xhtml";
		} else {
			// empIdがデータベースに存在しない場合。また、returnをnullにする
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"社員番号 not found", "社員番号が登録されていないため、ログインできません。"));
			return null;
		}
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Boolean getLoginVerify() {
		return loginVerify;
	}

	public void setLoginVerify(Boolean loginVerify) {
		this.loginVerify = loginVerify;
	}

}
