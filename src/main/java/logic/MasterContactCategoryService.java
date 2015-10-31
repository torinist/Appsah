package logic;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import model.MasterContactCategory;
import dao.MasterContactCategoryDao;

@Dependent
public class MasterContactCategoryService {

	@Inject
	MasterContactCategoryDao categoryDao;

	public List<MasterContactCategory> categoryCreate() {
		List<MasterContactCategory> categorys = categoryDao.findAllByDelflag();

		return categorys;
	}
}
