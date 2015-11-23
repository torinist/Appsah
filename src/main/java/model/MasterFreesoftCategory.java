package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the master_freesoft_category database table.
 * 
 */
@Entity
@Table(name="master_freesoft_category")
@NamedQuery(name="MasterFreesoftCategory.findAll", query="SELECT m FROM MasterFreesoftCategory m")
public class MasterFreesoftCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String lastupdate;

	private String memo;

	private String name;

	//bi-directional many-to-one association to Freesoft
	@OneToMany(mappedBy="masterFreesoftCategory")
	private List<Freesoft> freesofts;

	public MasterFreesoftCategory() {
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

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Freesoft> getFreesofts() {
		return this.freesofts;
	}

	public void setFreesofts(List<Freesoft> freesofts) {
		this.freesofts = freesofts;
	}

	public Freesoft addFreesoft(Freesoft freesoft) {
		getFreesofts().add(freesoft);
		freesoft.setMasterFreesoftCategory(this);

		return freesoft;
	}

	public Freesoft removeFreesoft(Freesoft freesoft) {
		getFreesofts().remove(freesoft);
		freesoft.setMasterFreesoftCategory(null);

		return freesoft;
	}

}