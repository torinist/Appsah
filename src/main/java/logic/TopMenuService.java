package logic;

import java.util.ArrayList;
import java.util.List;

import bean.TopMenuBean;

public class TopMenuService {

	public List<TopMenuBean> topMenuCreate() {
		// 本来ならDBから取得する
		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();
		TopMenuBean tm1 = new TopMenuBean();
		tm1.setParent("15年春アニメランキング");
		List<String> children1 = new ArrayList<String>();
		children1.add("響け！ ユーフォニアム");
		children1.add("やはり俺の青春ラブコメはまちがっている。続");
		children1.add("グリザイアの迷宮");
		children1.add("ダンジョンに出会いを求めるのは間違っているだろうか");
		tm1.setChildren(children1);
		tmList.add(tm1);
		
		TopMenuBean tm2 = new TopMenuBean();
		tm2.setParent("14年冬アニメランキング");
		List<String> children2 = new ArrayList<String>();
		children2.add("冴えない彼女の育てかた");
		children2.add("憑物語");
		children2.add("アルドノア・ゼロ");
		children2.add("純潔のマリア");
		children2.add("黒子のバスケ");
		children2.add("デュラララ！！×2　承");
		tm2.setChildren(children2);
		tmList.add(tm2);
		
		return tmList;
	}
}
