package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import model.TopMenu;
import util.Constant;
import util.StringUtil;
import bean.MenuBean;
import bean.TopMenuBean;
import dao.TopMenuDao;

@Dependent
public class TopMenuService implements Serializable {

	@Inject
	TopMenuDao topmenuDao;

	public List<TopMenuBean> topMenuCreate() throws Exception {
		List<TopMenu> list = topmenuDao.findAllByDelflag();
		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();

		// TODO:for文のたびに初めから検索かけているので要領が悪い。別のやり方を探したい

		try {
			for(TopMenu t : list) {
				if(t.getParentId()==null) {
					TopMenuBean tm = new TopMenuBean();
					MenuBean mb = new MenuBean(t.getId(), t.getName(), StringUtil.byteToString(t.getMenucontents(), Constant.CHARASET), t.getMember().getId(), t.getMember().getName(), t.getLastupdate());
					tm.setParent(mb);
					List<MenuBean> children = new ArrayList<MenuBean>();
					String parentId = t.getId();
					for(TopMenu tc : list) {
						if(tc.getParentId()!=null) {
							if(parentId.equals(tc.getParentId())) {
								MenuBean mbc = new MenuBean(tc.getId(), tc.getName(), StringUtil.byteToString(tc.getMenucontents(), Constant.CHARASET), tc.getMember().getId(), tc.getMember().getName(), tc.getLastupdate());
								children.add(mbc);
							}
						}
					}
					tm.setChildren(children);
					tmList.add(tm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return tmList;
	}
}
