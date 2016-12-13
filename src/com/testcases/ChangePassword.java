package com.testcases;

import java.io.File;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.inputmethod.Utf7ImeHelper;

public class ChangePassword extends UiAutomatorTestCase {
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));
	UiObject etOldPwd = new UiObject(
			new UiSelector()
					.resourceId("com.mappn.gfan:id/uc_changepsw_et_old_psw"));
	UiObject etNewPwd = new UiObject(
			new UiSelector()
					.resourceId("com.mappn.gfan:id/uc_changepsw_et_new_psw"));
	UiObject etConfirmPwd = new UiObject(
			new UiSelector()
					.resourceId("com.mappn.gfan:id/uc_changepsw_et_confirm_psw"));
	UiObject makeSureBtn = new UiObject(
			new UiSelector()
					.resourceId("com.mappn.gfan:id/uc_changepsw_btn_make_sure"));

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

	public void signIn(String name, String pwd)
			throws UiObjectNotFoundException {
		UiObject username = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_user_name"));
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_user_psd"));
		UiObject signIn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		username.setText(Utf7ImeHelper.e(name));
		password.setText(Utf7ImeHelper.e(pwd));
		signIn.clickAndWaitForNewWindow();
		UiObject userIcon = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/logged_iv_icon"));
		assertTrue(userIcon.exists());
	}

	public void openPersonCenter() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject my = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/userCenterRadio"));
		my.click();
		UiObject signBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		if (signBtn.exists()) {
			signIn("imopan507", "q123456");
		}
		UiObject userIcon = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/logged_iv_icon"));
		userIcon.clickAndWaitForNewWindow();

	}

	public void testChangePwd() throws UiObjectNotFoundException {
		openPersonCenter();
		new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_password"))
				.click();
		// 旧密码错误
		etOldPwd.setText(Utf7ImeHelper.e("qazx1234"));
		etNewPwd.setText(Utf7ImeHelper.e("123456"));
		etConfirmPwd.setText(Utf7ImeHelper.e("123456"));
		makeSureBtn.clickAndWaitForNewWindow();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/旧密码错误.png"));
		// 新密码与确认密码不一致
		etOldPwd.clearTextField();
		etOldPwd.setText(Utf7ImeHelper.e("q123456"));
		etNewPwd.clearTextField();
		etNewPwd.setText(Utf7ImeHelper.e("qwsss23232"));
		etConfirmPwd.clearTextField();
		etConfirmPwd.setText(Utf7ImeHelper.e("123456"));
		makeSureBtn.clickAndWaitForNewWindow();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/两次密码不一致.png"));
		// 新密码与旧密码相同
		etOldPwd.clearTextField();
		etOldPwd.setText(Utf7ImeHelper.e("q123456"));
		etNewPwd.clearTextField();
		etNewPwd.setText(Utf7ImeHelper.e("654321"));
		etConfirmPwd.clearTextField();
		etConfirmPwd.setText(Utf7ImeHelper.e("654321"));
		makeSureBtn.clickAndWaitForNewWindow();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/新密码与旧密码相同.png"));
		// 输入正确的旧密码、新密码、确认密码
		etOldPwd.clearTextField();
		etOldPwd.setText(Utf7ImeHelper.e("q123456"));
		etNewPwd.clearTextField();
		etNewPwd.setText(Utf7ImeHelper.e("w123456"));
		etConfirmPwd.clearTextField();
		etConfirmPwd.setText(Utf7ImeHelper.e("w123456"));
		makeSureBtn.clickAndWaitForNewWindow();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/密码修改成功.png"));
		signIn("imopan507", "w123456");
		backImage.click();

	}

}
