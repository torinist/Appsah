package backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
// import javax.faces.view.ViewScoped;
import javax.inject.Named;

import logic.TopMenuService;

import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

import bean.ContactBean;
import bean.TopMenuBean;

@Named
@RequestScoped
public class IndexBackingBean {

	private List<ContactBean> conb;
	
	private MenuModel menuModel;
	
	@PostConstruct
	public void init() {
		conb = new ArrayList<ContactBean>();
		conb.add(new ContactBean("主人公は東京の物理学校（東京理科大学の前身）を卒業したばかりの江戸っ子気質で血気盛んで無鉄砲な新任教師である。漱石が高等師範学校（後の東京高等師範学校）英語嘱託となって赴任を命ぜられ、愛媛県尋常中学校（松山東高校の前身）で1895年（明治28年）4月から教鞭をとり、1896年（明治29年）4月に熊本の第五高等学校へ赴任するまでの体験を下敷きに、後年書いた小説である。", "Wikipedia（坊ちゃん）"));
		conb.add(new ContactBean("『雪国』（ゆきぐに）は、川端康成の長編小説で、名作として国内外で名高い。雪国を訪れた男が、温泉町でひたむきに生きる女たちの諸相、ゆらめき、定めない命の各瞬間の純粋を見つめる物語[1]。愛し生きる女の情熱の美しく哀しい徒労が、男の無情に研ぎ澄まされた鏡のような心理の抒情に映されながら、美的に抽出されて描かれている。", "Wikipedia（雪国）"));
		conb.add(new ContactBean("九州の田舎（福岡県の旧豊前側）から出てきた小川三四郎が、都会の様々な人との交流から得るさまざまな経験、恋愛模様が描かれている。三四郎や周囲の人々を通じて、当時の日本が批評される側面もある。三人称小説であるが、視点は三四郎に寄り添い、ときに三四郎の内面にはいる。", "Wikipedia（三四郎）"));
		conb.add(new ContactBean("『道草』（みちくさ）は、夏目漱石の長編小説。「朝日新聞」に、1915年6月3日から9月14日まで掲載された。", "Wikipedia（道草）"));
		conb.add(new ContactBean("『羅生門』（らしょうもん）は、芥川龍之介の小説。『今昔物語集』の本朝世俗部巻二十九「羅城門登上層見死人盗人語第十八」を基に、巻三十一「太刀帯陣売魚姫語第三十一」の内容を一部に交える形で書かれたものである。生きるための悪という人間のエゴイズムを克明に描き出し、また、作者の解釈を加えた作品として著名である。高校教科書などでも採用され、広く知られている。", "Wikipedia（羅生門）"));
		conb.add(new ContactBean("私が自分の祖父のある事を知ったのは、私の母が産後の病気で死に、その後二月程経って不意に祖父が私の前に現れてきた、その時であった。私の六歳の時であった。", "暗夜行路"));
		conb.add(new ContactBean("朝、食堂でスウプを一さじ、すっと吸ってお母さまが、「あ」と幽（カス）かな叫び声をおあげになった。「髪の毛？」スウプに何か、イヤなものでも入っていたのかしら、と思った。", "斜陽"));
		conb.add(new ContactBean("千早振る神無月ももはや跡二日の余波となッた二十八日の午後三時頃に、神田見附の内より、塗渡る蟻、散る蜘蛛の子とうようよぞよぞよ沸出でて来るのは、孰（イズ）れも顋（オトガイ）を気にし給う方々。", "浮雲"));
		conb.add(new ContactBean("石炭をばはや積み果てつ。中等室の卓のほとりはいと静かにて、熾熱燈（シネツトウ）の光の晴れがましきも徒なり。", "舞姫"));
		conb.add(new ContactBean("廻れば大門の見返り柳いと長けれど、お歯ぐろ溝（ドブ）に灯火うつる三階の騒ぎも手に取る如く、明けくれなしの車の行来にはかり知られぬ全盛をうらないて……。", "たけくらべ"));
		conb.add(new ContactBean("未だ宵ながら松立てる門は一様に鎖籠めて、真直ぐに長く東より西に横はれる大道は掃きたるやうに物の影を留めず、いと寂しく往来の絶えたるに、例ならず繁き車輪の輾（キシリ）は、あるいは忙かりし、", "金色夜叉"));
		conb.add(new ContactBean("蓮華寺は下宿を兼ねた。瀬川丑松が急に転宿（ヤドガエ）を思い立って、借りることにした部屋というのは、その蔵裏（クリ）つづきにある二階の角のところ。", "破戒"));
		conb.add(new ContactBean("月日は百代の過客にして、行かふ年も又旅人なり。舟の上に生涯をうかべ、馬の口とらへて老をむかふる者は、日々旅にして旅を栖とす。古人も多く旅に死せるあり。", "奥の細道"));
		conb.add(new ContactBean("つれづれなるままに、日ぐらしすずりにむかひて、心にうつりゆくよしなしごとを、そこはかとなく書きつくれば、あやしうこそものぐるほしけれ。", "徒然草"));
		conb.add(new ContactBean("行く河の流れは絶えずして、しかも、もとの水にあらず。よどみに浮ぶうたかたは、かつ消え、かつ結びて、久しくとどまりたる例なし。", "方丈記"));
		conb.add(new ContactBean("祇園精舎の鐘の声、諸行無常の響きあり。娑羅双樹の花の色、盛者必衰の理をあらはす。おごれる人も久しからず。唯春の夜の夢のごとし。", "平家物語"));
		
		TopMenuService service = new TopMenuService();
		List<TopMenuBean> tmList = new ArrayList<TopMenuBean>();
		tmList = service.topMenuCreate();

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

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
	
	// contactを追加するメソッド
	public void viewContactEdit() {
		RequestContext.getCurrentInstance().openDialog("contactEdit");
	}
	
}
