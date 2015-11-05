package bean;

import java.io.Serializable;
import java.util.List;

/*
 *  入れ子メニュークラス
 */
public class TopMenuBean implements Serializable {

	private MenuBean parent; // 親メニュー
	private List<MenuBean> children; // 子メニュー

	public TopMenuBean() {
		// デフォルトコンストラクタ
	}

	public MenuBean getParent() {
		return parent;
	}

	public void setParent(MenuBean parent) {
		this.parent = parent;
	}

	public List<MenuBean> getChildren() {
		return children;
	}

	public void setChildren(List<MenuBean> children) {
		this.children = children;
	}

}
