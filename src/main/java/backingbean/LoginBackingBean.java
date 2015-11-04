package backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import logic.LoginService;
import model.Member;

import org.slf4j.Logger;

import util.LoginVerifier;
import bean.LoginUserBean;

@Named
@RequestScoped
public class LoginBackingBean {

	private String empId;

	@Inject
	LoginService loginService;

	@Inject
	LoginVerifier loginVerifier;

	@Inject
	LoginUserBean loginUser;

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
			return "index?faces-redirect=true";
		}
	}

	public String login() {
		try {
			Member member = loginService.login(empId);
			loginUser.login(member.getId(), member.getName(), member.getMasterMemcat().getId(), member.getMasterMemcat().getName());
			logger.info("ログイン成功：" + empId);
			return "index?faces-redirect=true";
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, empId + "：not found", e.getMessage()));
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
