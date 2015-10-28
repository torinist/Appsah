package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the top_menu database table.
 * 
 */
@Entity
@Table(name="top_menu")
@NamedQuery(name="TopMenu.findAll", query="SELECT t FROM TopMenu t")
public class TopMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String name;

	@Column(name="PARENT_ID")
	private String parentId;

	private String restricter;

	private String url;

	public TopMenu() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte getDelflag() {
		return this.delflag;
	}

	public void setDelflag(byte delflag) {
		this.delflag = delflag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRestricter() {
		return this.restricter;
	}

	public void setRestricter(String restricter) {
		this.restricter = restricter;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}