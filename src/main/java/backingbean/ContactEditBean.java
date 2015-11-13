package backingbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import logic.ContactService;
import logic.MasterContactCategoryService;
import model.MasterContactCategory;

import org.slf4j.Logger;

import util.LoginVerifier;
import bean.ContactBean;
import bean.LoginUserBean;

@Named
@RequestScoped
public class ContactEditBean {

	/**
	 *
	 */

	String content;

	// // contentを登録した人のID
	// private String writerId;

	String categoryId;

	List<MasterContactCategory> categorys;

	@Inject
	private MasterContactCategoryService categoryService;

	@Inject
	ContactService contactService;

	@Inject
	LoginVerifier loginVerifier;

	@Inject
	LoginUserBean loginUser;

	// ログインした人の名前を表示するための変数
	String loginUserName;

	@Inject
	Logger logger;

	@PostConstruct
	public void init() {
		displayInit();
	}

	/*
	 * submitボタン押下時のメソッド
	 */
	public void edit() {
		if (loginVerifier.loginVerify()) {
			logger.info("loginしていないみたいだね");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN",
							"ログインしないと登録できないよ！"));
		} else {
			contactService.contactAdd(new ContactBean(0, content, loginUser
					.getUserId(), null, categoryId, null, null));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
							"登録完了したよ！"));
			displayInit();
		}
	}

	/*
	 * 画面部品を初期化するメソッド
	 */
	private void displayInit() {
		content = null;
		if (loginVerifier.loginVerify()) {
			loginUserName = "ログインしていません";
		} else {
			loginUserName = loginUser.getUserName();
		}

		categorys = categoryService.categoryCreate();
		// writerIds = writerService.writerCreate();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// public String getWriterId() {
	// return writerId;
	// }
	//
	// public void setWriterId(String writerId) {
	// this.writerId = writerId;
	// }

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<MasterContactCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<MasterContactCategory> categorys) {
		this.categorys = categorys;
	}

	// public List<WriterBean> getWriterIds() {
	// return writerIds;
	// }
	//
	// public void setWriterIds(List<WriterBean> writerIds) {
	// this.writerIds = writerIds;
	// }

}
