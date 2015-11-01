package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import model.TopMenu;
import bean.TopMenuBean;
import dao.TopMenuDao;

@Dependent
public class TopMenuService implements Serializable {

	@Inject
	TopMenuDao topmenuDao;

	public List<TopMenuBean> topMenuCreate() {
		List<TopMenu> list = topmenuDao.findAllByDelflag();
		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();

		// TODO:for文のたびに初めから検索かけているので要領が悪い。別のやり方を探したい

		for(TopMenu t : list) {
			if(t.getParentId()==null) {
				TopMenuBean tm = new TopMenuBean();
				tm.setParent(t);
				List<TopMenu> children = new ArrayList<TopMenu>();
				String parentId = t.getId();
				for(TopMenu tc : list) {
					if(tc.getParentId()!=null) {
						if(parentId.equals(tc.getParentId())) {
							children.add(tc);
						}
					}
				}
				tm.setChildren(children);
				tmList.add(tm);
			}
		}
		return tmList;
	}
}
