package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the member database table.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Member.FIND_ALL, query="SELECT m FROM Member m"),
	@NamedQuery(name=Member.FIND_ALL_BY_DELFLAG, query="SELECT m FROM Member m WHERE m.delflag='false'")
})
public class Member implements Serializable {

	public static final String FIND_ALL = "Member.findAll";
	public static final String FIND_ALL_BY_DELFLAG = "Member.findAllByDelflag";
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String name;

	//bi-directional many-to-one association to MasterMemcat
	@ManyToOne
	@JoinColumn(name="MEMCAT")
	private MasterMemcat masterMemcat;

	public Member() {
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

	public MasterMemcat getMasterMemcat() {
		return this.masterMemcat;
	}

	public void setMasterMemcat(MasterMemcat masterMemcat) {
		this.masterMemcat = masterMemcat;
	}

}