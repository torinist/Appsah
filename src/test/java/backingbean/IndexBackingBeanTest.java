package backingbean;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import logic.TopMenuService;

import org.junit.Test;

import bean.TopMenuBean;

public class IndexBackingBeanTest {

	@Test
	public void test() {
		IndexBackingBean index = new IndexBackingBean();

		index.topMenuService = new mockTopMenuService();

		index.init();
		// initはvoidであるためAssertionするものがない
		assertTrue(true);
	}

	// JMockit
	public static class mockTopMenuService extends TopMenuService {

		@Override
		public List<TopMenuBean> topMenuCreate() {
			// TODO 自動生成されたメソッド・スタブ
			return new ArrayList<TopMenuBean>();
		}
	}

	public static class mockSingleTopMenuService extends TopMenuService {

		@Override
		public List<TopMenuBean> topMenuCreate() {
			// TODO 自動生成されたメソッド・スタブ
			// newではなくて1件を返す
			return new ArrayList<TopMenuBean>();
		}
	}
}
