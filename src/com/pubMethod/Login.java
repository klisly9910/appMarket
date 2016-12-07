package com.pubMethod;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.inputmethod.Utf7ImeHelper;

public class Login extends UiAutomatorTestCase {
	/**
	 * 登录界面-输入用户名-输入密码-点击登录->登录成功
	 * @throws UiObjectNotFoundException
	 */
	public void login() throws UiObjectNotFoundException {
		UiObject username = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_user_name"));
		username.click();
		username.setText(Utf7ImeHelper.e("imopan501"));
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_user_psd"));
		password.click();
		password.setText(Utf7ImeHelper.e("123456"));
		UiObject signBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		signBtn.click();
	}

}
