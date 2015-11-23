package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the freesoft database table.
 * 
 */
@Entity
@NamedQuery(name="Freesoft.findAll", query="SELECT f FROM Freesoft f")
public class Freesoft implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String adddate;

	private String addmem;

	private byte delflag;

	private String filepath;

	private String memo;

	private String name;

	private String version;

	//bi-directional many-to-one association to MasterFreesoftCategory
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	private MasterFreesoftCategory masterFreesoftCategory;

	public Freesoft() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdddate() {
		return this.adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public String getAddmem() {
		return this.addmem;
	}

	public void setAddmem(String addmem) {
		this.addmem = addmem;
	}

	public byte getDelflag() {
		return this.delflag;
	}

	public void setDelflag(byte delflag) {
		this.delflag = delflag;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
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

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public MasterFreesoftCategory getMasterFreesoftCategory() {
		return this.masterFreesoftCategory;
	}

	public void setMasterFreesoftCategory(MasterFreesoftCategory masterFreesoftCategory) {
		this.masterFreesoftCategory = masterFreesoftCategory;
	}

}