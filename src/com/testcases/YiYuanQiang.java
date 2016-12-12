package com.testcases;

import java.io.File;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.pubMethod.Login;
import com.pubMethod.Logout;

public class YiYuanQiang extends UiAutomatorTestCase {
	UiObject netError = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/netErrorView"));
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));
	UiObject my = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/userCenterRadio"));

	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();
	}

	/**
	 * 从首页点击两次手机物理返回按钮退出机锋市场
	 */
	public void tearDown() throws UiObjectNotFoundException {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}

	/**
	 * 验证点：一元抢首页遍历可见的商品，点击检查商品详情页是否有网络错误 测试过程： -点击机锋市场首页一元抢--遍历首页并点击进入商品详情页
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testHomeList() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject home = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/homeRadio"));
		home.click();
		UiCollection homeList = new UiCollection(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/yyq_home_rlv_list"));
		if (homeList.exists()) {
			int count = homeList.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/yyq_home_item_iv_icon"));
			for (int i = 0; i < count; i++) {
				UiObject homeListIcon = new UiObject(new UiSelector()
						.resourceId("com.mappn.gfan:id/yyq_home_item_iv_icon")
						.instance(i));
				homeListIcon.click();
				assertFalse(netError.exists());
				backImage.click();
			}

		}
		backImage.click();

	}

	/**
	 * 验证点：一元抢首页加入清单 测试过程： -进入一元抢首页-点击加入清单：- 已登录机锋市场：点击加入清单并截图保存
	 * -未登录机锋市场：调用登录-登录后自动返回到一元抢首页
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testJiaRuQingDan() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject home = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/homeRadio"));
		home.click();
		UiObject buy = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/yyq_home_item_tv_buy"));
		buy.click();
		UiObject signBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		if (signBtn.exists()) {
			Login login = new Login();
			login.login();
			my.click();
			Logout logout = new Logout();
			logout.logout();
			backImage.click();

		} else if (!signBtn.exists()) {
			System.out.println("has login");
			File path = new File("sdcard/download/jiaruqingdan.png");
			UiDevice.getInstance().takeScreenshot(path);
			backImage.click();
		}

	}

	/**
	 * 验证点：商品详情页未出现网络错误，参与记录显示 测试过程： -一元抢首页-点击一个商品-进入商品详情页-检查详情页是否出现网络错误
	 * -已登录机锋市场：检查参与记录 -未登录机锋市场：调用登录方法登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testProductDetail() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject home = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/homeRadio"));
		home.click();
		UiObject itemIcon = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/yyq_home_item_iv_icon"));
		itemIcon.click();
		UiObject detailList = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/yyq_detail_rv_list"));
		assertTrue(detailList.exists());
		UiObject loginText = new UiObject(new UiSelector().text("登录 查看您的参与记录"));
		UiObject recordText = new UiObject(new UiSelector().text("您没有参与本期抢宝"));
		if (loginText.exists()) {
			loginText.click();
			Login login = new Login();
			login.login();
			backImage.click();
			my.click();
			Logout logout = new Logout();
			logout.logout();
			backImage.click();

		} else if (recordText.exists()) {
			System.out.println("has login!");
			backImage.click();
			backImage.click();
		}

	}

	/**
	 * 验证点：揭晓页面未出现网络异常；遍历已开奖/未开奖，并分别点击进入商品详情页 测试过程： -点击揭晓-获取揭晓lists：
	 * -遍历已开奖的商品信息-进入获奖者个人人中心-检查显示是否正确 -遍历未开奖的商品-点击进入详情页-检查未开奖状态显示是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testJieXiao() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject jiexiao = new UiObject(new UiSelector().text("揭晓"));
		jiexiao.click();
		UiCollection awardList = new UiCollection(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/yyq_open_award_rlv_list"));
		if (awardList.exists()) {
			int count = awardList
					.getChildCount(new UiSelector()
							.resourceId("com.mappn.gfan:id/gm3_yyq_award_item_iv_icon"));
			for (int i = 0; i < count; i++) {
				UiObject itemIcon = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/gm3_yyq_award_item_iv_icon")
						.instance(i));
				itemIcon.click();
				assertFalse(netError.exists());
				backImage.click();

			}
			int doOpenCount = awardList
					.getChildCount(new UiSelector()
							.resourceId("com.mappn.gfan:id/gm3_yyq_award_item_rl_do_open"));
			for (int j = 0; j < doOpenCount; j++) {
				UiObject doOpen = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/gm3_yyq_award_item_rl_do_open")
						.instance(j));
				doOpen.click();
				assertFalse(netError.exists());
				UiObject otherIcon = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/other_iv_icon"));
				assertTrue(otherIcon.exists());
				backImage.click();

			}

			int notOpenCount = awardList
					.getChildCount(new UiSelector()
							.resourceId("com.mappn.gfan:id/gm3_yyq_award_item_rl_donot_open"));
			for (int k = 0; k < notOpenCount; k++) {
				UiObject notOpen = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/gm3_yyq_award_item_rl_donot_open")
						.instance(k));
				notOpen.click();
				assertFalse(netError.exists());
				UiObject lotteryWait = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/gm3_yyq_detail_rl_lottery_wait"));
				assertTrue(lotteryWait.exists());
				backImage.click();

			}

		}
		backImage.click();
	}

	/**
	 * 【验证点】：清单未出现网络错误；清单数量显示；参与人数增减；编辑-删除 【测试过程】： -点击清单 -未登录：调用登录方法 ：已登录：
	 * -有商品：商品数量显示正确；点击+/-后商品参数人数显示正确 -没有商品：提示信息显示正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testQingDan() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject qingdan = new UiObject(new UiSelector().text("清单"));
		qingdan.click();
		UiObject signBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		UiCollection billList = new UiCollection(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/bill_lv_list_view"));
		UiObject itemCount = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/bill_item_et_count"));
		UiObject minus = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/bill_item_iv_minus"));
		UiObject plus = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/bill_item_iv_plus"));
		UiObject billNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/bill_tv_num"));

		if (signBtn.exists()) {
			Login login = new Login();
			login.login();
			qingdan.click();
		}

		if (billList.exists()) {
			int billCount = billList.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/bill_ll_layout"));
			String billNumTex = billNum.getText();
			System.out.println(billNumTex.substring(1, 2));
			// billNumTex.substring(1, 2);
			assertEquals("商品件数", Integer.parseInt(billNumTex.substring(1, 2)),
					billCount);
			// assertTrue("商品件数", Integer.parseInt(billNumTex)==billCount);
			int beforeClickPlus = Integer.parseInt(itemCount.getText());
			plus.click();
			int afterClickPlus = Integer.parseInt(itemCount.getText());
			assertEquals("点击+号后", beforeClickPlus + 1, afterClickPlus);
			minus.click();
			int afterClickMinus = Integer.parseInt(itemCount.getText());
			assertEquals("", afterClickMinus, afterClickPlus - 1);

		} else if (!billList.exists()) {
			UiObject noBillList = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/empty_frame_layout"));
			assertTrue(noBillList.exists());

		}
		Logout logout = new Logout();
		logout.logout();
		backImage.click();
	}
	/**
	 * 验证点：删除清单
	 * 测试过程：
	 * -已登录，直接进入清单页面
	 * -未登录：调用登录方法，登录后点击进入清单页面
	 * -清单页有订单：删除前截图-点击编辑-选择一笔订单-点击删除-点击提示[确定]-删除后截图，对比两张截图
	 * -清单页没有订单：验证[开始抢宝]按钮存在-点击[开始抢宝]-验证跳转到一元抢首页
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testDeleteQingDan() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject qingdan = new UiObject(new UiSelector().text("清单"));
		qingdan.click();
		UiObject signBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		if (signBtn.exists()) {
			Login login = new Login();
			login.login();
			qingdan.click();
		}
		UiCollection billList = new UiCollection(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/bill_lv_list_view"));
		if (billList.exists()) {

			UiObject edit = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/bill_tv_edit"));
			edit.clickAndWaitForNewWindow(2000);
			//删除订单前截图
			UiDevice.getInstance().takeScreenshot(
					new File("/sdcard/download/beforeDelete.png"));
			UiObject checkBox = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/bill_item_cb_check"));
			checkBox.click();
			UiObject deleteBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/bill_tv_delete_submit"));
			deleteBtn.click();
			UiObject positiveBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/btn_positive"));
			positiveBtn.clickAndWaitForNewWindow();
			//删除订单后截图
			UiDevice.getInstance().takeScreenshot(
					new File("/sdcard/download/afterDelete.png"));
			backImage.click();
		} else if (!billList.exists()) {
			UiObject emptyGo = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/bill_empty_go"));
			assertTrue(emptyGo.exists());
			emptyGo.click();
			UiObject homeList = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/yyq_home_rlv_list"));
			assertTrue(homeList.exists());
			backImage.click();
		}
	}
}
