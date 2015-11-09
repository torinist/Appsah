package backingbean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import logic.TopMenuService;

import org.primefaces.model.menu.MenuModel;

import bean.MenuBean;
import bean.TopMenuBean;

@Named
@RequestScoped
public class ShareInfoBackingBean {
	private MenuModel menuModel;

	@Inject
	TopMenuService topMenuService;

	List<TopMenuBean> tmList;

	@Inject
	org.slf4j.Logger Logger;

	@PostConstruct
	public void init() {
		try {
			tmList = topMenuService.topMenuCreate();
		} catch (Exception e) {
			// TODO: 例外処理
		}
//
//		menuModel = new DefaultMenuModel();
//
//		// TODO: メニューの組み合わせを考えたほうがよさそう。メニューとして表示させるか、ただのlinkとして表示させるか
//		// TODO: 思い切って単なるHTMLにするというのも手かも。htmlとjavaの接続にはJAX-RS使うとか
//		for (TopMenuBean tm : tmList) {
//			DefaultSubMenu dsm = new DefaultSubMenu(tm.getParent().getName());
//			System.out.println(tm.getParent().getName());
//			// List<String> children = new ArrayList<String>();
//			for (TopMenu child : tm.getChildren()) {
//				// 親（dsm）の要素としてitemを追加
//				DefaultMenuItem item = new DefaultMenuItem(child.getName());
//				dsm.addElement(item);
//			}
//			// 親と子のセット（dsm）をmenuModelに追加
//			menuModel.addElement(dsm);
//		}
	}

	// TODO dialog表示にしているが、参照等と同じページの方が良いのかは考えもの
	public String addMenu() {
		Logger.info("addMenuに突入します");
		// TODO：ホントはダイアログ表示にしたい
//		Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
//		TopMenuBean tmb = (TopMenuBean)map.get("tm");
//
//		// dialogにparentのidを受け渡してdialogを開く
//		RequestContext rq = RequestContext.getCurrentInstance();
//		rq.addCallbackParam("parentId", tmb.getParent().getId());
//		rq.getCurrentInstance().openDialog("menuContentsEdit");

		Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		TopMenuBean tmb = (TopMenuBean)map.get("tm");

		// 次のページに遷移する
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("parentId", tmb.getParent().getId());

		return "menuContentsEdit?faces-redirect=true";

	}

	public String clickMenu() {
		// JSFアプリケーションの現在の状態を保持しているオブジェクト
		FacesContext context = FacesContext.getCurrentInstance();
		// ExternalContextはRequestやSessionスコープの中で管理されているオブジェクトを取り出すことができる
		ExternalContext exContext = context.getExternalContext();
		// getRequestMapはRequestスコープで管理されている情報をMapで取得する
		Map map = exContext.getRequestMap();
		// Mapからchild変数に対応付けられているオブジェクトが取得できる
		MenuBean child = (MenuBean)map.get("child");

		// 次のページに遷移する
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("parentId", child.getParentId());
		flash.put("menuId", child.getId());

		return "menuContentsEdit?faces-redirect=true";

//		Map<String, List<String>> param = new HashMap<String, List<String>>();
//		List<String> list = new ArrayList<String>();
//		list.add(child.getId());
//		list.add(child.getName());
//		list.add(child.getUrl());
//		param.put("child", list);
//
//		RequestContext.getCurrentInstance().openDialog("textResourcesShow", null, param);
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public List<TopMenuBean> getTmList() {
		return tmList;
	}

	public void setTmList(List<TopMenuBean> tmList) {
		this.tmList = tmList;
	}

}
