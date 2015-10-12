package bean;

import java.util.List;

/*
 *  入れ子メニュークラス
 */
public class TopMenuBean {

	private String parent;			// 親メニュー
	private List<String> children;	// 子メニュー

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

}
