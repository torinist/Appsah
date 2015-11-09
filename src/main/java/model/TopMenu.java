package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the top_menu database table.
 *
 */
@Entity
@Table(name = "top_menu")
@NamedQueries({
		@NamedQuery(name = TopMenu.FIND_ALL, query = "SELECT t FROM TopMenu t"),
		@NamedQuery(name = TopMenu.FIND_ALL_BY_DELFLAG, query = "SELECT t FROM TopMenu t WHERE t.delflag='false'"),
		@NamedQuery(name = TopMenu.FIND_PARENT, query = "SELECT t FROM TopMenu t WHERE t.delflag='false' AND t.parentId=null"),
		@NamedQuery(name = TopMenu.FIND_BY_PARENTID, query = "SELECT t FROM TopMenu t WHERE t.parentId=:ParentID")})
public class TopMenu implements Serializable {
	public final static String FIND_ALL = "TopMenu.findAll";
	public final static String FIND_ALL_BY_DELFLAG = "TopMenu.findAllByDelflag";
	public final static String FIND_BY_PARENTID = "TopMenu.findByParentId";
	public final static String FIND_PARENT = "TopMenu.findParent";

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String lastupdate;

	@ManyToOne
	@JoinColumn(name = "LASTUPMEMBER")
	private Member member;

	@Lob
	private byte[] menucontents;

	private String name;

	@Column(name = "PARENT_ID")
	private String parentId;

	// RESTRICTERが3であれば、1、2の権限を認める、といった仕組み
	@ManyToOne
	@JoinColumn(name = "RESTRICTER")
	private MasterMemcat memcat;

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

	public String getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public byte[] getMenucontents() {
		return this.menucontents;
	}

	public void setMenucontents(byte[] menucontents) {
		this.menucontents = menucontents;
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

	public MasterMemcat getMemcat() {
		return memcat;
	}

	public void setMemcat(MasterMemcat memcat) {
		this.memcat = memcat;
	}

}