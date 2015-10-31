package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import model.MasterContactCategory;

@Stateless
public class MasterContactCategoryDao extends BaseDao<MasterContactCategory> {

	public MasterContactCategoryDao() {
		this(MasterContactCategory.class);
	}

	public MasterContactCategoryDao(Class<MasterContactCategory> entityClass) {
		super(entityClass);
	}

	public List<MasterContactCategory> findAllByDelflag() {
		Query q = super.createNamedQuery(MasterContactCategory.FIND_ALL);
		return q.getResultList();
	}

}
