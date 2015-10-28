package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import model.Contact;
import util.Tracer;
import bean.ContactBean;
import dao.ContactDao;

@Interceptors(Tracer.class)
@Dependent
public class ContactService implements Serializable {

	@Inject
	ContactDao conDao;

	public List<ContactBean> contactListCreate() {
		List<Contact> list = conDao.findAllByDelflag();
		List<ContactBean> conb = new ArrayList<ContactBean>();

		for (Contact c : list) {
			ContactBean cb = new ContactBean(c.getId(), c.getContents(),
					c.getMember().getId(), c.getMember().getName(),
					c.getMasterContactCategory().getId(),
					c.getMasterContactCategory().getName(),
					c.getDate().toString());
			conb.add(cb);
		}

		return conb;
	}
}
