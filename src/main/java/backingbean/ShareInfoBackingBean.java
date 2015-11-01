package backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.TopMenuService;
import model.TopMenu;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import bean.TopMenuBean;

@Named
@RequestScoped
public class ShareInfoBackingBean {
	private MenuModel menuModel;

	@Inject
	TopMenuService topMenuService;

	@PostConstruct
	public void init() {
		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();
		tmList = topMenuService.topMenuCreate();

		menuModel = new DefaultMenuModel();

		// TODO: メニューの組み合わせを考えたほうがよさそう。メニューとして表示させるか、ただのlinkとして表示させるか
		// TODO: 思い切って単なるHTMLにするというのも手かも。htmlとjavaの接続にはJAX-RS使うとか
		for (TopMenuBean tm : tmList) {
			DefaultSubMenu dsm = new DefaultSubMenu(tm.getParent().getName());
			System.out.println(tm.getParent().getName());
			// List<String> children = new ArrayList<String>();
			for (TopMenu child : tm.getChildren()) {
				// 親（dsm）の要素としてitemを追加
				DefaultMenuItem item = new DefaultMenuItem(child.getName());
				dsm.addElement(item);
			}
			// 親と子のセット（dsm）をmenuModelに追加
			menuModel.addElement(dsm);
		}
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

}
