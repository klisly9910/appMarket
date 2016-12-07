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

	public void tearDown() throws UiObjectNotFoundException {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}

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
}
