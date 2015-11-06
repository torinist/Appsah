package logic;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import model.MasterMemcat;
import model.Member;
import model.TopMenu;

import org.slf4j.Logger;

import util.Constant;
import util.Dateutil;
import util.StringUtil;
import bean.MenuBean;
import bean.TopMenuBean;
import dao.TopMenuDao;

@Dependent
public class TopMenuService implements Serializable {

	@Inject
	TopMenuDao topmenuDao;

	@Inject
	Logger logger;

	public List<TopMenuBean> topMenuCreate() throws Exception {
		List<TopMenu> list = topmenuDao.findAllByDelflag();
		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();

		// TODO:for文のたびに初めから検索かけているので要領が悪い。別のやり方を探したい

		try {
			for (TopMenu t : list) {
				if (t.getParentId() == null) {
					TopMenuBean tm = new TopMenuBean();
					MenuBean mb = new MenuBean(t.getId(), t.getName(),
							StringUtil.byteToString(t.getMenucontents(),
									Constant.CHARASET), t.getMember().getId(),
							t.getMember().getName(), t.getLastupdate(),
							t.getParentId(), t.getMemcat().getId(), t
									.getMemcat().getName());
					tm.setParent(mb);
					List<MenuBean> children = new ArrayList<MenuBean>();
					String parentId = t.getId();
					for (TopMenu tc : list) {
						if (tc.getParentId() != null) {
							if (parentId.equals(tc.getParentId())) {
								MenuBean mbc = new MenuBean(tc.getId(),
										tc.getName(), StringUtil.byteToString(
												tc.getMenucontents(),
												Constant.CHARASET), tc
												.getMember().getId(), tc
												.getMember().getName(),
										tc.getLastupdate(), t.getParentId(), t
												.getMemcat().getId(), t
												.getMemcat().getName());
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

	/**
	 * メニューのコンテンツを1件取得する
	 * @param menuId　メニューID
	 * @return　IDに該当するメニューオブジェクト
	 * @throws UnsupportedEncodingException　サポートされていないエンコードを指定した場合に発生する
	 */
	public MenuBean findMenu(String menuId) throws UnsupportedEncodingException {
		TopMenu tm = topmenuDao.find(menuId);
		try {
			MenuBean mb = new MenuBean(tm.getId(), tm.getName(),
					StringUtil.byteToString(tm.getMenucontents(),
							Constant.CHARASET), tm.getMember().getId(), tm
							.getMember().getName(), tm.getLastupdate(),
					tm.getParentId(), tm.getMemcat().getId(), tm.getMemcat()
							.getName());
			return mb;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void editMenu(MenuBean mb) {
		// MenuBeanをTopMenuに変換する
		TopMenu tm = new TopMenu();
		tm.setId(mb.getId());
		tm.setName(mb.getName());
		tm.setParentId(mb.getParentId());
		tm.setDelflag((byte) 0);
		tm.setLastupdate(Dateutil.dateToString(Dateutil.getNowDate()));

		Member m = new Member();
		m.setId(mb.getLastupMemberId());
		m.setName(mb.getLastupMemberName());
		tm.setMember(m);

		try {
			tm.setMenucontents(StringUtil.stringToByte(mb.getMenucontents(),
					Constant.CHARASET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		MasterMemcat mm = new MasterMemcat();
		mm.setId(mb.getRestricterId());
		mm.setName(mb.getRestricterName());
		tm.setMemcat(mm);

		// TopMenuをmergeする
		topmenuDao.edit(tm);
	}

}
