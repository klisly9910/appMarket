package com.testcases;


import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.pubMethod.AppName;
import com.pubMethod.IfErrorMessageExist;

public class YingYong extends UiAutomatorTestCase {
	IfErrorMessageExist errorMessage = new IfErrorMessageExist();
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));
	UiObject netError = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/netErrorView"));
	UiCollection recyclerView = new UiCollection(
			new UiSelector().resourceId("com.mappn.gfan:id/recyclerView"));

	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();
	}

	public void tearDown() throws UiObjectNotFoundException {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}

	/**
	 * 调用IfErrorMessageExist类方法 验证点：应用-分类-分类子页面加载未出现网络错误
	 * 测试过程：-点击应用-分类-分类子列表，验证页面加载未出现未落错误
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testYingYongFenLei() throws UiObjectNotFoundException {
		UiObject yingyong = new UiObject(new UiSelector().text("应用"));
		yingyong.click();
		errorMessage.ErrorMessageExist();
		backImage.click();

	}

	/**
	 * 调用AppName类的方法验证 验证点：验证详情页未出现网络错误 测试过程：-排行-遍历可见的应用并点击进入详情页-验证详情页未出现网络错误
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPaiHang() throws UiObjectNotFoundException {
		UiObject yingyong = new UiObject(new UiSelector().text("应用"));
		yingyong.click();
		UiObject paihang = new UiObject(new UiSelector().text("排行"));
		paihang.click();
		AppName appName = new AppName();
		appName.clickApp();
		backImage.click();

	}

	/**
	 * 调用AppName类的方法验证 验证点：验证详情页未出现网络错误 测试过程：-排行-遍历可见的应用并点击进入详情页-验证详情页未出现网络错误
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTuiJian() throws UiObjectNotFoundException {
		UiObject yingyong = new UiObject(new UiSelector().text("应用"));
		yingyong.click();
		UiObject tuijian = new UiObject(new UiSelector().text("推荐"));
		tuijian.click();
		AppName appName = new AppName();
		appName.clickApp();
		backImage.click();
	}

	/**
	 * 调用AppName类的方法验证 验证点：验证详情页未出现网络错误 测试过程：-排行-遍历可见的应用并点击进入详情页-验证详情页未出现网络错误
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testXinPing() throws UiObjectNotFoundException {
		UiObject yingyong = new UiObject(new UiSelector().text("应用"));
		yingyong.click();
		UiObject xinping = new UiObject(new UiSelector().text("新品"));
		xinping.click();
		AppName appName = new AppName();
		appName.clickApp();
		backImage.click();
	}
	/**
	 * 验证点：锋神榜未出现网络错误-点击进入详情页未出现网络错误
	 * 测试过程：-点击游戏-点击锋神榜-验证锋神榜未出现网络错误，（遍历）点击进入详情页-验证游戏详情页未出现网络错误
	 * @throws UiObjectNotFoundException
	 */
	public void testFengShenBang() throws UiObjectNotFoundException {
		UiObject yingyong = new UiObject(new UiSelector().text("应用"));
		yingyong.click();
		UiObject fengshenbang = new UiObject(new UiSelector().text("锋神榜"));
		fengshenbang.click();
		assertFalse(netError.exists());
		if (recyclerView.exists()) {
			int count = recyclerView.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/gfan_bill_board_app_name"));
			for (int i = 0; i < count; i++) {
				UiObject iconURL = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/gfan_bill_board_app_name").instance(
						i));
				iconURL.click();
				assertFalse(netError.exists());
				backImage.click();

			}

		}
		backImage.click();
	}

}
