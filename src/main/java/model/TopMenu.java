package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the top_menu database table.
 *
 */
@Entity
@Table(name="top_menu")
@NamedQueries({
	@NamedQuery(name=TopMenu.FIND_ALL, query="SELECT t FROM TopMenu t"),
	@NamedQuery(name=TopMenu.FIND_ALL_BY_DELFLAG, query="SELECT t FROM TopMenu t WHERE t.delflag='false'")
})

public class TopMenu implements Serializable {
	public final static String FIND_ALL = "TopMenu.findAll";
	public final static String FIND_ALL_BY_DELFLAG = "TopMenu.findAllByDelflag";
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String name;

	@Column(name="PARENT_ID")
	private String parentId;

	private String restricter;

	@Lob
	private byte[] menufile;

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

	public byte[] getMenufile() {
		return this.menufile;
	}

	public void setMenufile(byte[] menufile) {
		this.menufile = menufile;
	}

}