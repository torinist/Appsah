package backingbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import logic.TopMenuService;
import util.LoginVerifier;
import bean.LoginUserBean;
import bean.MenuBean;

@Named
@ViewScoped
public class MenuContentsEditBackingBean implements Serializable {

	@Inject
	TopMenuService topMenuService;

	@Inject
	MenuBean menu;

	@Inject
	LoginVerifier loginVerifier;

	@Inject
	LoginUserBean loginUser;

	// 参照、更新のフラグ
	Boolean editable = false;

	// 新規作成のフラグ
	Boolean additional = false;

	// デフォルトはログアウト状態（true）
	Boolean loginVerify = true;

	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		String menuId = (String) flash.get("menuId");
		String parentId = (String) flash.get("parentId");

		// 初回はmenuIDの有無とparentIDの有無のみ、初回は更新はない

		if (menuId != null) {
			// menuIdがある場合
			// 参照
			editable = false;
			additional = false;
			try {
				menu = topMenuService.findMenu(menuId);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"選択された項目が見つかりませんでした", e.getMessage()));
			}

		} else {
			try {
				// 新規作成
				editable = true;
				additional = true;
				menu.setParentId(parentId);
				menu.setParentName(topMenuService.findMenu(parentId).getName());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"エラー", e.getMessage()));
			}

		}
	}

	// 画面表示時
	public String displayCheck() {
		loginCheck();
		if (editable) {
			if (additional) {
				// 新規作成時
				if (loginVerify) {
					// ログインさせるようにメッセージを出力する
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"ノーログイン", "新規作成にはログインが必要です"));
					editable = false;
					additional = true;
				} else {
					// ログインしているのでボタン等を出力
					// 権限制限が必要な場合はここにifを追加する？

				}
			} else {
				// 変更時
				if (loginVerify) {
					// ログインさせるようにメッセージを出力する
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"ノーログイン", "ログインしていないため更新できません"));
					editable = false;
					additional = false;
				} else {
					// ログインしているのでボタン等を出力
				}
			}
		} else {
			if (additional) {
				// エラー画面を出力
			} else {
				// 参照時
				if (loginVerify) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"ノーログイン", "ログインしていないため参照のみできます"));
				}
			}
		}
		return null;
	}

	public void loginCheck() {
		loginVerify = loginVerifier.loginVerify();
	}

	// 変更と登録
	public String edit() {
		try {
			menu.setLastupMemberId(loginUser.getUserId());
			menu.setLastupMemberName(loginUser.getUserName());
			if(additional) {
				// TODO: mastermemcatは仮で入れる
				menu.setRestricterId("1");
				String menuid = topMenuService.addMenu(menu);
				menu.setId(menuid);
			} else {
				topMenuService.editMenu(menu);
			}

			editable = false;
			additional = false;

			Flash flash = FacesContext.getCurrentInstance().getExternalContext()
					.getFlash();
			flash.put("menuId", menu.getId());
			flash.put("parentId", menu.getParentId());

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"完了", "変更/登録完了しました"));

			return "menuContentsEdit?faces-redirect=true";

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"エラー発生", e.getMessage()));
			return null;
		}
	}

	public String remove() {
		menu.setLastupMemberId(loginUser.getUserId());
		menu.setLastupMemberName(loginUser.getUserName());

		try {
			topMenuService.removeMenu(menu);

			return "shareInfo?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"エラー発生", e.getMessage()));
			return null;
		}
	}

	public MenuBean getMenu() {
		return menu;
	}

	public void setMenu(MenuBean menu) {
		this.menu = menu;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getAdditional() {
		return additional;
	}

	public void setAdditional(Boolean additional) {
		this.additional = additional;
	}

}
