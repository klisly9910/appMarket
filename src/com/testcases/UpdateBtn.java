package com.testcases;

import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UpdateBtn extends UiAutomatorTestCase {
	UiObject updateBtn = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/updateBtn"));
	UiObject arrowImg = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/arrowImg"));
	UiObject detailBtn = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/detailBtn"));
	UiObject backImagBtn = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));
	UiObject ignoreBtn = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/ignoreBtn"));
	UiObject appName = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/appNameText"));
	UiObject noResult = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/promptText"));
	UiCollection recyclerView = new UiCollection(
			new UiSelector().resourceId("com.mappn.gfan:id/recyclerView"));

	/**
	 * 进入机锋市场
	 */
	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();
	}

	/**
	 * 退出机锋市场
	 */
	public void tearDown() {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();

	}

	/**
	 * 设备是否存在需要更新的应用： if：设备存在需要更新的应用 -设备有需要更新的应用
	 * -存在需要更新的应用->>点击应用管理->>验证是否进入升级页面 -验证升级页面[一键更新]更新数量是否与应用管理右上角数量是否一致
	 * -升级页面列表app数量与updateBtnNum是否一致 -返回机锋市场首页 -点击[详情]->>点击[忽略] -返回机锋市场首页 else
	 * if：设备不存在更新的应用
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdateBtn() throws UiObjectNotFoundException {

		UiObject updateNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/upgradeNum"));
		UiObject oneKeyUpdate = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/oneKeyUpdate"));
		UiObject oneKeyUpateBtnNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/numUpdateText"));

		if (updateNum.exists()) {
			String updateBtnNum = updateNum.getText();
			updateBtn.click();
			assertTrue("点击应用管理进入升级页面", oneKeyUpdate.exists());
			String oneKeyUpdateNum = oneKeyUpateBtnNum.getText();
			assertEquals(updateBtnNum, oneKeyUpdateNum);

			ArrayList<String> updatelists = new ArrayList<String>();
			if (recyclerView.exists()) {
				int count = recyclerView.getChildCount(new UiSelector()
						.resourceId("com.mappn.gfan:id/appNameText"));
				for (int i = 0; i < count; i++) {
					UiObject updateAppName = new UiObject(new UiSelector()
							.resourceId("com.mappn.gfan:id/appNameText")
							.instance(i));
					updatelists.add(i, updateAppName.getText());

				}
				assertEquals("升级页面列表app数量与updateBtnNum是否一致",
						Integer.parseInt(updateBtnNum), count);
				System.out.println("update app name :" + updatelists);

			}

			arrowImg.click();
			detailBtn.click();
			backImagBtn.click();
			ignoreBtn.click();
			ignoreBtn.click();
			UiObject unIgnoreBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/unIgnoreBtn"));
			unIgnoreBtn.click();
			backImagBtn.click();

		} else if (!updateNum.exists()) {
			updateBtn.click();
			UiObject noResultView = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/nullResultView"));
			assertTrue("设备没有需要更新的应用", noResultView.exists());
			String noResultText = noResult.getText();
			assertEquals("你很勤快嘛，继续保持哦~", noResultText);

		}

		backImagBtn.click();

	}

	/**
	 * 任务：有下载/无下载 -有下载任务： -点击[详情]->>返回任务列表->>点击[删除]->>验证应用是否被删除(通过任务列表数量是否减少判断)
	 * -没有下载任务->>验证[你怎么忍心让我空着呢，快去精品推荐看看吧~]
	 */
	public void testTask() throws UiObjectNotFoundException {
		updateBtn.click();
		new UiObject(new UiSelector().text("任务")).click();
		UiObject installInfo = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/installedInfoView"));
		UiObject unInstall = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/uninstallBtn"));
		if (installInfo.exists()) {
			// UiCollection recyclerView = new UiCollection(
			// new UiSelector()
			// .resourceId("com.mappn.gfan:id/recyclerView"));
			String appNameText = appName.getText();
			arrowImg.click();
			detailBtn.click();
			assertEquals(appName.getText(), appNameText);
			backImagBtn.click();
			int beforeDeleteCount = recyclerView.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/appNameText"));
			unInstall.click();
			int afterDeleteCount = beforeDeleteCount - 1;
			assertEquals(afterDeleteCount + 1, beforeDeleteCount);
			ArrayList<String> appList = new ArrayList<String>();
			if (recyclerView.exists()) {
				for (int i = 0; i < afterDeleteCount; i++) {
					UiObject app = new UiObject(new UiSelector().resourceId(
							"com.mappn.gfan:id/appNameText").instance(i));
					appList.add(i, app.getText());

				}

			}
			System.out.println("删除一个应用后剩余应用:" + appList);

		} else if (!installInfo.exists()) {
			assertEquals("你怎么忍心让我空着呢，快去精品推荐看看吧~", noResult.getText());
		}
		backImagBtn.click();
	}

	/**
	 * 安装包管理： -
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPackage() throws UiObjectNotFoundException {
		updateBtn.click();
		new UiObject(new UiSelector().text("安装包管理")).click();
		UiObject setUpInfo = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/setupInfoView"));
		if (setUpInfo.exists()) {
			ArrayList<String> appname = new ArrayList<String>();
			if (recyclerView.exists()) {
				int count = recyclerView.getChildCount(new UiSelector()
						.resourceId("com.mappn.gfan:id/appNameText"));
				for (int i = 0; i < count; i++) {
					UiObject appName = new UiObject(new UiSelector()
							.resourceId("com.mappn.gfan:id/appNameText")
							.instance(i));
					appname.add(i, appName.getText());

				}
				System.out.println("package appname :" + appname);
				UiObject deletePackage = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/deleteView"));
				deletePackage.click();
				int afterCount = count - 1;
				assertEquals(afterCount + 1, count);
			} else if (!setUpInfo.exists()) {
				assertEquals("你很勤快嘛，继续保持哦~", noResult.getText());
			}
		}
		backImagBtn.click();

	}
}
