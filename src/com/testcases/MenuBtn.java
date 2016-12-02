package com.testcases;

import java.io.File;
import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class MenuBtn extends UiAutomatorTestCase {
	UiObject menuBtn = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/menuBtn"));
	UiObject backBtn = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));

	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();
	}

	public void tearDown() throws UiObjectNotFoundException {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}

	/**
	 * 验证点：加速功能 -测试过程：点击加速->>截图
	 * 
	 * @throws UiObjectNotFoundException
	 * 
	 */
	public void testSpeed() throws UiObjectNotFoundException {
		menuBtn.click();
		new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/sv_go_speed"))
				.click();
		UiDevice.getInstance().takeScreenshot(
				new File("sdcard/Download/speed.png"));
		// UiDevice.getInstance().pressBack();

	}

	/**
	 * 验证点：获取侧拉菜单列表 -测试过程：通过遍历获取menulist菜单text
	 */
	public void testMenuList() throws UiObjectNotFoundException {
		menuBtn.click();
		UiCollection menus = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/menu_listview"));
		int count = menus.getChildCount(new UiSelector()
				.resourceId("com.mappn.gfan:id/menu_text"));
		String[] actualMenuName = new String[] { "我的", "反馈", "一元抢", "检查更新",
				"论坛", "设置" };
		ArrayList<String> menuList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < actualMenuName.length; j++) {
				UiObject menuName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/menu_text").instance(i));
				String menuNameText = menuName.getText();
				menuList.add(i, menuNameText);
				// assertEquals(actualMenuName[j],menuNameText);

			}
		}
		System.out.println("menulist name:" + menuList);
		// UiDevice.getInstance().pressBack();

	}

	/**
	 * 验证点：侧拉菜单-我的页面(登录/未登录) 测试过程： -未登录账号：遍历个人中心->>点击每一个页面->>验证登录按钮是否存在
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPersonCenter() throws UiObjectNotFoundException {
		menuBtn.click();
		UiObject my = new UiObject(new UiSelector().text("我的"));
		// assertTrue("我的 存在", my.exists());
		my.click();
		UiObject pleaseLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_name"));
		if (pleaseLogin.getText().equals("请登录")) {
			UiCollection logged = new UiCollection(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/logged_scroll_view"));
			if (logged.exists()) {
				int count = logged.getChildCount(new UiSelector()
						.className("android.widget.TextView"));
				ArrayList<String> personCenterList = new ArrayList<String>();
				for (int i = 0; i < count; i++) {
					UiObject textView = new UiObject(new UiSelector()
							.className("android.widget.TextView").instance(i));
					String name = textView.getText();
					personCenterList.add(i, name);
					textView.click();
					UiObject signInBtn = new UiObject(
							new UiSelector()
									.resourceId("com.mappn.gfan:id/tv_sign_in"));
					 assertTrue(signInBtn.exists());
					backBtn.click();
				}
				System.out.println("person center list name :"
						+ personCenterList);

			}
		}
		UiDevice.getInstance().pressBack();
	}
}
