package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the contact database table.
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Contact.FIND_ALL, query = "SELECT c FROM Contact c"),
		@NamedQuery(name = Contact.FIND_ALL_BY_DELFLAG, query = "SELECT c FROM Contact c WHERE c.delflag='false'") })
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Contact.findAll";
	public static final String FIND_ALL_BY_DELFLAG = "Contact.findAllByDelflag";

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "ADDMEM")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "CATEGORY")
	private MasterContactCategory masterContactCategory;

	private String contents;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private byte delflag;

	public Contact() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MasterContactCategory getMasterContactCategory() {
		return masterContactCategory;
	}

	public void setMasterContactCategory(
			MasterContactCategory masterContactCategory) {
		this.masterContactCategory = masterContactCategory;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public byte getDelflag() {
		return this.delflag;
	}

	public void setDelflag(byte delflag) {
		this.delflag = delflag;
	}

}