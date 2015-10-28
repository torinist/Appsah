package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import model.Member;
import bean.WriterBean;
import dao.MemberDao;

@ApplicationScoped
public class WriterService implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	MemberDao memDao;

	public List<WriterBean> writerCreate() {

		List<Member> list = memDao.findAllByDelflag();
		List<WriterBean> writers = new ArrayList<WriterBean>();

		for (Member m : list) {
			WriterBean wb = new WriterBean(m.getId(), m.getName());
			writers.add(wb);
		}

		return writers;
	}
}
