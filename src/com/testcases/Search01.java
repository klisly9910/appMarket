package com.testcases;

import java.util.ArrayList;
import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.inputmethod.Utf7ImeHelper;

public class Search01 extends UiAutomatorTestCase {
	/**
	 * 打开机锋市场
	 */
	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();

	}

	/**
	 * 返回机锋市场首页
	 */
	public void tearDown() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/backBtn"))
				.click();

	}

	/**
	 * 点击搜索框->>输入不存在的appname(大富豪)->>点击搜索按钮->>判断是否存在(敬请期待~)提示信息
	 * 在搜索结果，搜索框输入(百度)->>点击搜索按钮->>遍历搜索结果->>取出搜索结果可见的APP名字 搜索结果，判断第一个app按钮状态并点击按钮
	 * 搜索(百度音乐)->>点击搜索结果第一个APP进入详情页->>验证详情页appname与点击的appname一致
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testsearch() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/keyText"))
				.click();
		new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/searchEdit"))
				.setText(Utf7ImeHelper.e("大富豪"));
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/searchBtn"))
				.click();
		UiObject noResult = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/promptText"));
		String noResText = noResult.getText();
		Assert.assertEquals("敬请期待~", noResText);
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/keyText"))
				.setText(Utf7ImeHelper.e("百度"));
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/searchBtn"))
				.click();
		UiCollection resultLists = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/recyclerView"));
		ArrayList<String> appnames = new ArrayList<String>();
		if (resultLists.exists()) {
			int count = resultLists.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/appNameText"));
			for (int i = 0; i < count; i++) {
				UiObject appname = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/appNameText").instance(i));
				if (appname.exists()) {
					appnames.add(i, appname.getText());
				}

			}

		}
		System.out.println("search result appname :" + appnames);
		UiObject actionText = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/actionText"));
		if (actionText.getText().equalsIgnoreCase("下载")) {
			actionText.click();
			Assert.assertEquals("暂停", actionText.getText());
		} else if (actionText.getText().equalsIgnoreCase("打开")) {
			actionText.click();
			device.pressBack();
			device.pressBack();
		} else if (actionText.getText().equalsIgnoreCase("更新")) {
			actionText.click();
			Assert.assertEquals("暂停", actionText.getText());
		} else if (actionText.getText().equalsIgnoreCase("继续")) {
			actionText.click();
			Assert.assertEquals("暂停", actionText.getText());
		} else if (actionText.getText().equalsIgnoreCase("暂停")) {
			actionText.click();
			Assert.assertEquals("继续", actionText.getText());
		} else {
			Assert.assertEquals("安装", actionText.getText());
			System.out.println("button status is install");
		}
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/keyText"))
				.click();
		new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/searchEdit"))
				.setText(Utf7ImeHelper.e("百度音乐"));
		new UiObject(new UiSelector().resourceId("com.mappn.gfan:id/searchBtn"))
				.click();
		UiObject clickapp = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/appNameText"));
		String clickappName = clickapp.getText();
		clickapp.click();
		String afterClickName = clickapp.getText();
		Assert.assertEquals(clickappName, afterClickName);
		new UiObject(new UiSelector().className("android.widget.ImageButton"))
				.click();

	}

}
