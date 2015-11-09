package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import model.TopMenu;

@Stateless
public class TopMenuDao extends BaseDao<TopMenu>{

	public TopMenuDao() {
		this(TopMenu.class);
	}

	public TopMenuDao(Class<TopMenu> entityClass) {
		super(entityClass);
	}

	public List<TopMenu> findAllByDelflag() {
		Query q = super.createNamedQuery(TopMenu.FIND_ALL_BY_DELFLAG);
		return q.getResultList();
	}

	public List<TopMenu> findByParentId(String parentId) {
		Query q = super.createNamedQuery(TopMenu.FIND_BY_PARENTID);
		q.setParameter("ParentID", parentId);
		return q.getResultList();
	}

	public List<TopMenu> findParent() {
		Query q = super.createNamedQuery(TopMenu.FIND_PARENT);
		return q.getResultList();
	}
}
