package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Contact;

@Stateless
public class ContactDao extends BaseDao<Contact>{

	public ContactDao() {
		this(Contact.class);
	}

	public ContactDao(Class<Contact> entityClass) {
		super(entityClass);
	}

	public List<Contact> findAllByDelflag() {
		Query q = super.createNamedQuery(Contact.FIND_ALL_BY_DELFLAG);
		return q.getResultList();
	}

}
