package com.testcases;

import java.io.File;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.inputmethod.Utf7ImeHelper;
import com.pubMethod.Login;

public class PersonCenteryyq extends UiAutomatorTestCase {
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));

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
	 * 此类的公共方法： -点击一元抢-点击我的 -判断是否登录： 未登录： -调用登录方法-登录成功后-点击头像-进入个人中心
	 * -已登录-点击头像-进入个人中心
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void login() throws UiObjectNotFoundException {
		UiObject yyq = new UiObject(new UiSelector().text("一元抢"));
		yyq.click();
		UiObject my = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/userCenterRadio"));
		my.click();
		UiObject signBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_sign_in"));
		if (signBtn.exists()) {
			Login login = new Login();
			login.login();
			UiDevice.getInstance().takeScreenshot(
					new File("/sdcard/download/afterlogin.png"));
		}
		UiObject userIcon = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/logged_iv_icon"));
		userIcon.clickAndWaitForNewWindow();
	}

	/**
	 * 验证点：个人中心头像、昵称显示 -调用进入个人中心的login()方法 -验证username显示 -验证usericon显示
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testBaseInfo() throws UiObjectNotFoundException {
		login();
		UiObject userName = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_username"));
		assertTrue(userName.exists());
		UiObject uIcon = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/user_iv_icon"));
		assertTrue(uIcon.exists());
		UiObject exit = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_exit"));
		exit.click();
		UiObject positive = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_positive"));
		positive.click();
		backImage.click();

	}

	/**
	 * 验证点：修改昵称
	 * 测试过程：
	 * -获取修改前昵称Text
	 * -直接点击【提交】
	 * -输入特殊字符-点击【提交】-截图-返回
	 * -输入正常字符-点击【提交】-截图-返回-截图-判断修改前后昵称不一致
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testEditNickName() throws UiObjectNotFoundException {
		login();
		UiObject EditNickName = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_tv_nick_name"));
		assertTrue(EditNickName.exists());
		String beforenickNameText = EditNickName.getText();
		EditNickName.click();
		UiObject submit = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/kit_btn_change_nickname_commit"));
		UiObject changeNickName = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/kit_et_change_nickname"));
		submit.clickAndWaitForNewWindow();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/直接点击提交按钮.png"));
		changeNickName.setText(Utf7ImeHelper.e("%^%$%$%"));
		submit.clickAndWaitForNewWindow();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/输入特殊字符.png"));
//		changeNickName.clickBottomRight();
//		changeNickName.clearTextField();
		backImage.click();
		EditNickName.click();
		changeNickName.setText(Utf7ImeHelper.e("imopantest"));
		submit.click();
		String afterEdit = EditNickName.getText();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/修改后的昵称.png"));
		assertNotSame("修改昵称前后对比昵称不一致", afterEdit, beforenickNameText);
		UiObject exit = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_exit"));
		exit.click();
		UiObject positive = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_positive"));
		positive.click();
		backImage.click();

	}

}
