package logic;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
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

	/**
	 * menuの作成
	 * @return menuListを返す
	 * @throws Exception
	 */
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
							t.getParentId(), null, t.getMemcat().getId(), t
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
												tc.getLastupdate(), tc.getParentId(),
												topmenuDao.find(tc.getParentId()).getName(),
												tc.getMemcat().getId(), tc.getMemcat().getName());
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
	 * 親メニューのみ取得する
	 * @return
	 */
	public List<MenuBean> getParentMenu() {
		return null;
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
			String parentName = null;
			if (tm.getParentId() != null) {
				parentName = topmenuDao.find(tm.getParentId()).getName();
			}
			MenuBean mb = new MenuBean(tm.getId(), tm.getName(),
					StringUtil.byteToString(tm.getMenucontents(),
							Constant.CHARASET), tm.getMember().getId(), tm
							.getMember().getName(), tm.getLastupdate(),
							tm.getParentId(), parentName,
							tm.getMemcat().getId(), tm.getMemcat().getName());
			return mb;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * 子メニューを追加する
	 * @param mb 追加する子メニュー
	 * @return 追加した子メニューのID
	 * @throws Exception　エラーが発生した場合
	 */
	public String addMenu(MenuBean mb) throws Exception {

		try {
			TopMenu tm = new TopMenu();
			tm.setId(getTopMenuNewId(mb.getParentId()));
			tm.setLastupdate(Dateutil.dateToString(Dateutil.getNowDate()));

			Member member = new Member();
			member.setId(mb.getLastupMemberId());
			member.setName(mb.getLastupMemberName());
			tm.setMember(member);

			MasterMemcat memcat = new MasterMemcat();
			memcat.setId(mb.getRestricterId());
			tm.setMemcat(memcat);

			tm.setMenucontents(StringUtil.stringToByte(mb.getMenucontents(), Constant.CHARASET));
			tm.setName(mb.getName());
			tm.setParentId(mb.getParentId());
			tm.setDelflag((byte) 0);
			topmenuDao.create(tm);

			return tm.getId();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	* 指定したmenuを1件編集する
	* @param mb 追加する子メニュー
	 * @throws Exception エラーが発生した場合
	*/
	public void editMenu(MenuBean mb) throws Exception {
		try {
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
			tm.setMenucontents(StringUtil.stringToByte(mb.getMenucontents(),
					Constant.CHARASET));
			MasterMemcat mm = new MasterMemcat();
			mm.setId(mb.getRestricterId());
			mm.setName(mb.getRestricterName());
			tm.setMemcat(mm);

			// TopMenuをmergeする
			topmenuDao.edit(tm);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}


	}

	/**
	 * 引数のParentIdに紐づいているメニューの新しいIDを発行する
	 * @param parentId 親ID
	 * @return 子の新しいID
	 */
	public String getTopMenuNewId(String parentId) {
		List<TopMenu> childList = topmenuDao.findByParentId(parentId);
		childList.sort(new Comparator<TopMenu>() {
			@Override
			public int compare(TopMenu o1, TopMenu o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		Integer maxId = Integer.parseInt(childList.get((childList.size())-1).getId());
		maxId++;
		String newId = maxId.toString();

		return newId;
	}

	/**
	 * 1件メニューを削除する（論理削除）
	 * @param mb 削除したいメニュー
	 * @throws Exception エラーが発生した場合
	 */
	public void removeMenu(MenuBean mb) throws Exception {
		try {
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
			tm.setMenucontents(StringUtil.stringToByte(mb.getMenucontents(),
					Constant.CHARASET));
			MasterMemcat mm = new MasterMemcat();
			mm.setId(mb.getRestricterId());
			mm.setName(mb.getRestricterName());
			tm.setMemcat(mm);
			tm.setDelflag((byte)1);

			// TopMenuをmergeする
			topmenuDao.edit(tm);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
