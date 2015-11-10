package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

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
		this.userId = null;
		this.userName = null;
		this.memCatId = null;
		this.memCatName = null;
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
