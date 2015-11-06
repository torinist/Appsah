package bean;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

// トップメニューの中身
@Dependent
public class MenuBean implements Serializable {

	// menuのID
	private String id;

	// menuのname
	private String name;

	// menuのcontents
	private String menucontents;

	// 最終更新者ID
	private String lastupMemberId;

	// 最終更新者の名前
	private String lastupMemberName;

	// 最終更新日時
	private String lastupdate;

	// 親menuのID
	private String parentId;

	// 権限ID
	private String restricterId;

	// 権限名
	private String restricterName;

	public MenuBean() {
		// デフォルトコンストラクタ
	}

	public MenuBean(String id, String name, String menucontents,
			String lastupMemberId, String lastupMemberName, String lastupdate,
			String parentId, String restricterId, String restricterName) {
		this.id = id;
		this.name = name;
		this.menucontents = menucontents;
		this.lastupMemberId = lastupMemberId;
		this.lastupMemberName = lastupMemberName;
		this.lastupdate = lastupdate;
		this.parentId = parentId;
		this.restricterId = restricterId;
		this.restricterName = restricterName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenucontents() {
		return menucontents;
	}

	public void setMenucontents(String menucontents) {
		this.menucontents = menucontents;
	}

	public String getLastupMemberId() {
		return lastupMemberId;
	}

	public void setLastupMemberId(String lastupMemberId) {
		this.lastupMemberId = lastupMemberId;
	}

	public String getLastupMemberName() {
		return lastupMemberName;
	}

	public void setLastupMemberName(String lastupMemberName) {
		this.lastupMemberName = lastupMemberName;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRestricterId() {
		return restricterId;
	}

	public void setRestricterId(String restricterId) {
		this.restricterId = restricterId;
	}

	public String getRestricterName() {
		return restricterName;
	}

	public void setRestricterName(String restricterName) {
		this.restricterName = restricterName;
	}

}
