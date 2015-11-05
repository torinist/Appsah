package backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import bean.MenuBean;

@Named
@RequestScoped
public class MenuContentsDetailBackingBean {

	@Inject
	MenuBean menu;

	@PostConstruct
	public void init() throws Exception {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		menu = (MenuBean) flash.get("resources");
	}

	public MenuBean getMenu() {
		return menu;
	}

	public void setMenu(MenuBean menu) {
		this.menu = menu;
	}

}
