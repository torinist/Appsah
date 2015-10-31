package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import model.Contact;
import model.MasterContactCategory;
import model.Member;
import util.Dateutil;
import bean.ContactBean;
import dao.ContactDao;

//@Interceptors(Tracer.class)
@Dependent
public class ContactService implements Serializable {

	@Inject
	ContactDao conDao;

	public List<ContactBean> contactListCreate() {
		List<Contact> list = conDao.findAllByDelflag();
		List<ContactBean> conb = new ArrayList<ContactBean>();

		for(int i=list.size();i>0;i--) {
			Contact c = list.get(i-1);
			ContactBean cb = new ContactBean(c.getId(), c.getContents(),
					c.getMember().getId(), c.getMember().getName(),
					c.getMasterContactCategory().getId(),
					c.getMasterContactCategory().getName(),
					c.getDate().toString());
			conb.add(cb);
		}

		return conb;
	}

	/*
	 * 例外が返ってこない場合は0を返す
	 */
	public void contactAdd(ContactBean contactBean) {
		Contact contact = new Contact();
		MasterContactCategory mcc = new MasterContactCategory();
		Member member = new Member();

		contact.setContents(contactBean.getContent());
		contact.setDate(Dateutil.dateToString(Dateutil.getNowDate()));
		mcc.setId(contactBean.getCategoryId());
		contact.setMasterContactCategory(mcc);
		member.setId(contactBean.getWriterId());
		contact.setMember(member);
		conDao.create(contact);
	}
}
