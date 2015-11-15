package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

@SessionScoped
public class LoginUserBean implements Serializable {

	// ログインユーザID
	private String userId;

	// ログインユーザ名
	private String userName;

	// ユーザ権限ID
	private String memCatId;

	// ユーザ権限名
	private String memCatName;

	private Logger logger;

	public LoginUserBean() {
		// デフォルトコンストラクタ
	}

	public void login(String userId, String userName, String memCatId, String memCatName) {
		this.userId = userId;
		this.userName = userName;
		this.memCatId = memCatId;
		this.memCatName = memCatName;
	}

	public void logout() {
		ExternalContext exCon = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession)exCon.getSession(false);
		if(session!=null) {
			try {
				session.invalidate();
			} catch(Exception e) {
				// TODO: セッション破棄失敗ログなどを記述する
				logger.error("正常にセッションが破棄されませんでした。");
			}
		}
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getMemCatId() {
		return memCatId;
	}

	public String getMemCatName() {
		return memCatName;
	}

}
