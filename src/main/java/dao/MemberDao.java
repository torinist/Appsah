package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Member;

@Stateless
public class MemberDao extends BaseDao<Member>{

	public MemberDao() {
		this(Member.class);
	}

	public MemberDao(Class<Member> entityClass) {
		super(entityClass);
	}

	public List<Member> findAllByDelflag() {
		Query q = super.createNamedQuery(Member.FIND_ALL_BY_DELFLAG);
		return q.getResultList();
	}
}
