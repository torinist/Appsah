package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;



/**
 * The persistent class for the contact database table.
 *
 */
@Entity
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contents;

	private byte delflag;

	//bi-directional many-to-one association to MasterContactCategory
	@ManyToOne
	@JoinColumn(name="CATEGORY")
	private MasterContactCategory masterContactCategory;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="ADDMEM")
	private Member member;

	public Contact() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public byte getDelflag() {
		return this.delflag;
	}

	public void setDelflag(byte delflag) {
		this.delflag = delflag;
	}

	public MasterContactCategory getMasterContactCategory() {
		return this.masterContactCategory;
	}

	public void setMasterContactCategory(MasterContactCategory masterContactCategory) {
		this.masterContactCategory = masterContactCategory;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}