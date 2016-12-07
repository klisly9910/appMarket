package com.pubMethod;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Logout extends UiAutomatorTestCase {
	/**
	 * 我的-点击头像->个人中心-点击注销-点击确定-退出登录-显示登录界面
	 * @throws UiObjectNotFoundException
	 */
	public void logout() throws UiObjectNotFoundException {
		UiObject userIcon = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/logged_iv_icon"));
		userIcon.click();
		UiObject exit = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_exit"));
		exit.click();
		UiObject positive = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_positive"));
		positive.click();
	}

}
