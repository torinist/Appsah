package dao;

import model.Contact;

public class ContactDao extends BaseDao<Contact>{

	public ContactDao() {
		this(Contact.class);
	}
	public ContactDao(Class<Contact> entityClass) {
		super(entityClass);
	}

}
