package com.testcases;

import java.io.File;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.pubMethod.AppName;
import com.pubMethod.IfErrorMessageExist;

public class Game extends UiAutomatorTestCase {
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));
	UiObject netError = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/netErrorView"));

	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();
	}

	public void tearDown() throws UiObjectNotFoundException {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}

	/**
	 * 调用IfErrorMessageExist类方法 验证点：游戏-分类-分类子列表页面加载未出现网络错误
	 * 测试过程：-点击游戏-分类-分类子列表，验证页面加载未出现未落错误
	 * 
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void testGameFenLei() throws UiObjectNotFoundException,
			RemoteException {
		UiObject recentapp = new UiObject(
				new UiSelector()
						.resourceId("com.android.systemui:id/app_thumbnail"));
		UiObject game = new UiObject(new UiSelector().text("游戏"));
		game.click();
		IfErrorMessageExist errorMessage = new IfErrorMessageExist();
		errorMessage.ErrorMessageExist();
		try {
			backImage.click();
		} catch (Exception e) {
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().takeScreenshot(
					new File("sdcard/Download/duizhanpingtai.png"));
			UiDevice.getInstance().pressRecentApps();
			do {
				recentapp.waitForExists(2000);
				if (recentapp.exists()) {
					recentapp.swipeLeft(5);
				}
			} while (recentapp.exists());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 调用AppName类的方法验证 验证点：验证详情页未出现网络错误 测试过程：-排行-遍历可见的应用并点击进入详情页-验证详情页未出现网络错误
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTuiJian() throws UiObjectNotFoundException {
		UiObject youxi = new UiObject(new UiSelector().text("游戏"));
		youxi.click();
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
	public void testShouFa() throws UiObjectNotFoundException {
		UiObject youxi = new UiObject(new UiSelector().text("游戏"));
		youxi.click();
		UiObject tuijian = new UiObject(new UiSelector().text("首发"));
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
	public void testPaiHang() throws UiObjectNotFoundException {
		UiObject youxi = new UiObject(new UiSelector().text("游戏"));
		youxi.click();
		UiObject paihang = new UiObject(new UiSelector().text("排行"));
		paihang.click();
		AppName appName = new AppName();
		appName.clickApp();
		backImage.click();

	}

	/**
	 * 验证点：礼包中心未出现网络错误 测试过程：-点击游戏-点击礼包中心-验证礼包中心未出现网络错误
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testLiBao() throws UiObjectNotFoundException {
		UiObject youxi = new UiObject(new UiSelector().text("游戏"));
		youxi.click();
		UiObject libao = new UiObject(new UiSelector().text("礼包中心"));
		libao.click();
		assertFalse(netError.exists());
		backImage.click();
	}

}
