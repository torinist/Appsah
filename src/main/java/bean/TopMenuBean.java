package bean;

import java.io.Serializable;
import java.util.List;

import model.TopMenu;

/*
 *  入れ子メニュークラス
 */
public class TopMenuBean implements Serializable {

	private TopMenu parent; // 親メニュー
	private List<TopMenu> children; // 子メニュー

	public TopMenuBean() {
		// デフォルトコンストラクタ
	}

	public TopMenu getParent() {
		return parent;
	}

	public void setParent(TopMenu parent) {
		this.parent = parent;
	}

	public List<TopMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TopMenu> children) {
		this.children = children;
	}

}
