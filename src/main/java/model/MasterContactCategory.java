package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the master_contact_category database table.
 *
 */
@Entity
@Table(name="master_contact_category")
@NamedQueries({
	@NamedQuery(name=MasterContactCategory.FIND_ALL, query="SELECT m FROM MasterContactCategory m"),
	@NamedQuery(name=MasterContactCategory.FIND_ALL_BY_DELFLAG, query="SELECT m FROM MasterContactCategory m WHERE m.delflag='false'")
})
public class MasterContactCategory implements Serializable {

	public static final String FIND_ALL = "MasterContactCategory.findAll";
	public static final String FIND_ALL_BY_DELFLAG = "MasterContactCategory.findAllByDelflag";
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte delflag;

	private String name;

//	//bi-directional many-to-one association to Contact
//	@OneToMany(mappedBy="masterContactCategory")
//	private List<Contact> contacts;

	public MasterContactCategory() {
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

//	public List<Contact> getContacts() {
//		return this.contacts;
//	}
//
//	public void setContacts(List<Contact> contacts) {
//		this.contacts = contacts;
//	}
//
//	public Contact addContact(Contact contact) {
//		getContacts().add(contact);
//		contact.setMasterContactCategory(this);
//
//		return contact;
//	}
//
//	public Contact removeContact(Contact contact) {
//		getContacts().remove(contact);
//		contact.setMasterContactCategory(null);
//
//		return contact;
//	}

}