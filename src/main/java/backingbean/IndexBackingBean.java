package backingbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.ContactService;
import logic.TopMenuService;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import bean.ContactBean;
import bean.TopMenuBean;

@Named
@RequestScoped
public class IndexBackingBean {

	private List<ContactBean> conb;

	private MenuModel menuModel;

	@Inject
	TopMenuService topMenuService;

	@Inject
	private ContactService contactService;

	@PostConstruct
	public void init() {
		conb = contactService.contactListCreate();

		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();
		tmList = topMenuService.topMenuCreate();

		menuModel = new DefaultMenuModel();

		for(TopMenuBean tm : tmList) {
			DefaultSubMenu dsm = new DefaultSubMenu(tm.getParent());
			// List<String> children = new ArrayList<String>();
			for(String child : tm.getChildren()) {
				// 親（dsm）の要素としてitemを追加
				DefaultMenuItem item = new DefaultMenuItem(child);
				dsm.addElement(item);
			}
			// 親と子のセット（dsm）をmenuModelに追加
			menuModel.addElement(dsm);
		}

	}

	public List<ContactBean> getConb() {
		return conb;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	// contactを追加するメソッド
	public void viewContactEdit() {
		RequestContext.getCurrentInstance().openDialog("contactEdit");
	}

}
