package com.testcases;

import java.io.File;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.inputmethod.Utf7ImeHelper;
import com.pubMethod.Login;

public class EditAddress extends UiAutomatorTestCase {
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
		// 点击用户头像进入个人中心页面
		UiObject userIcon = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/logged_iv_icon"));
		userIcon.clickAndWaitForNewWindow();
	}

	public void logout() throws UiObjectNotFoundException {
		UiObject exit = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_exit"));
		exit.click();
		UiObject positive = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_positive"));
		positive.click();
	}

	public void addAddress() throws UiObjectNotFoundException {
		UiObject receiver = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/receiver"));
		receiver.setText(Utf7ImeHelper.e("机锋"));
		UiObject phoneNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/phoneNum"));
		phoneNum.setText(Utf7ImeHelper.e("15101112002"));
		// 滚动获取省份
		UiObject spProvince = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/spProvince"));
		spProvince.click();
		UiScrollable provinceList = new UiScrollable(
				new UiSelector()
						.resourceId("android:id/select_dialog_listview"));
		UiObject selectProvince = provinceList.getChildByText(
				new UiSelector().resourceId("android:id/text1"), "广西", true);
		selectProvince.click();
		// 滚动获取城市
		UiObject spCity = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/spCity"));
		spCity.click();
		UiScrollable cityList = new UiScrollable(
				new UiSelector()
						.resourceId("android:id/select_dialog_listview"));
		UiObject selectCity = cityList.getChildByText(
				new UiSelector().resourceId("android:id/text1"), "桂林", true);
		selectCity.click();
		// 滚动获取地区
		UiObject spDistrict = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/spDistrict"));
		spDistrict.click();
		UiScrollable districtList = new UiScrollable(
				new UiSelector()
						.resourceId("android:id/select_dialog_listview"));
		UiObject selectDistrict = districtList.getChildByText(
				new UiSelector().resourceId("android:id/text1"), "七星", true);
		selectDistrict.click();
		UiObject detailAddress = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/address"));
		detailAddress.setText(Utf7ImeHelper.e("中山南路 南城百货大厦101室"));
		UiObject defaultAddress = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/chooseAddress"));
		defaultAddress.click();
		UiObject saveBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/save_tv"));
		saveBtn.click();

	}

	public void editAddress() throws UiObjectNotFoundException {
		UiObject receiver = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ed_receiver"));
		receiver.clearTextField();
		receiver.setText(Utf7ImeHelper.e("机锋keji"));
		UiObject phoneNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ed_phoneNum"));
		phoneNum.clearTextField();
		phoneNum.setText(Utf7ImeHelper.e("15101112088"));
		// 滚动获取省份
		UiObject spProvince = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ed_spProvince"));
		spProvince.click();
		UiScrollable provinceList = new UiScrollable(
				new UiSelector()
						.resourceId("android:id/select_dialog_listview"));
		UiObject selectProvince = provinceList.getChildByText(
				new UiSelector().resourceId("android:id/text1"), "广东", true);
		selectProvince.click();
		// 滚动获取城市
		UiObject spCity = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ed_spCity"));
		spCity.click();
		UiScrollable cityList = new UiScrollable(
				new UiSelector()
						.resourceId("android:id/select_dialog_listview"));
		UiObject selectCity = cityList.getChildByText(
				new UiSelector().resourceId("android:id/text1"), "云浮", true);
		selectCity.click();
		// 滚动获取地区
		UiObject spDistrict = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ed_spDistrict"));
		spDistrict.click();
		UiScrollable districtList = new UiScrollable(
				new UiSelector()
						.resourceId("android:id/select_dialog_listview"));
		UiObject selectDistrict = districtList.getChildByText(
				new UiSelector().resourceId("android:id/text1"), "罗定", true);
		selectDistrict.click();
		UiObject detailAddress = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ed_address"));
		detailAddress.clearTextField();
		detailAddress.setText(Utf7ImeHelper.e("中山南路 南城百货大厦101室"));
		UiObject defaultAddress = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/chooseAddress"));
		defaultAddress.click();
		UiObject saveBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/save_tv"));
		saveBtn.click();
	}

	public void deleteAddress() throws UiObjectNotFoundException {
		new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/delete_address"))
				.click();
		new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_positive"))
				.click();
		UiDevice.getInstance().takeScreenshot(
				new File("/sdcard/download/删除地址后.png"));
	}

	public void testAddaddress() throws UiObjectNotFoundException {
		login();
		UiObject addressManager = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_address"));
		addressManager.click();
		UiObject addBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/btn_add_address"));
		UiObject rightAddBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/add_address_tv"));
		if (addBtn.exists()) {
			addBtn.click();
			addAddress();
			backImage.click();
			logout();
			backImage.click();
		} else if (!addBtn.exists()) {
			rightAddBtn.click();
			addAddress();
			backImage.click();
			logout();
			backImage.click();

		}

	}

	public void testEditAddress() throws UiObjectNotFoundException {
		login();
		UiObject addressManager = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_address"));
		addressManager.click();
		UiObject addressList = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/address_full_rv_recyclerview"));
		if (addressList.exists()) {
			new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/receiver_address"))
					.click();
			editAddress();
			backImage.click();
			logout();
			backImage.click();

		} else if (!addressList.exists()) {
			UiObject addBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/btn_add_address"));
			addBtn.click();
			addAddress();
			new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/receiver_address"))
					.click();
			editAddress();
			backImage.click();
			logout();
			backImage.click();
		}

	}

	public void testDeleteAddress() throws UiObjectNotFoundException {
		login();
		UiObject addressManager = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/user_layout_address"));
		addressManager.click();
		UiObject addressList = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/address_full_rv_recyclerview"));
		if (addressList.exists()) {
			new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/receiver_address"))
					.click();
			deleteAddress();
			backImage.click();
			logout();
			backImage.click();

		} else if (!addressList.exists()) {
			UiObject rightAddBtn = new UiObject(
					new UiSelector().resourceId("com.mappn.gfan:id/add_address_tv"));
			rightAddBtn.click();
			addAddress();
			deleteAddress();
			backImage.click();
			logout();
			backImage.click();
		}

	}

}
