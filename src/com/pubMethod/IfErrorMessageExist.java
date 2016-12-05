package com.pubMethod;

import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class IfErrorMessageExist extends UiAutomatorTestCase {
	UiObject netError = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/netErrorView"));
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));

	public void ErrorMessageExist() throws UiObjectNotFoundException {

		UiObject fenlei = new UiObject(new UiSelector().text("分类"));
		fenlei.click();
		assertFalse(netError.exists());
		UiCollection recyclerView = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/recyclerView"));
		ArrayList<String> fenleiList = new ArrayList<String>();
		if (recyclerView.exists()) {
			int count = recyclerView.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/text"));
			for (int i = 0; i < count; i++) {
				UiObject objText = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/text").instance(i));
				String text = objText.getText();
				fenleiList.add(i, text);
				objText.click();
				assertFalse(netError.exists());
				UiCollection fenleiSecondList = new UiCollection(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/tabLayout"));
				if (fenleiSecondList.exists()) {
					int secondCount = fenleiSecondList
							.getChildCount(new UiSelector()
									.className("android.support.v7.app.ActionBar$Tab"));
					for (int j = 0; j < secondCount; j++) {
						UiObject secondText = new UiObject(new UiSelector()
								.className(
										"android.support.v7.app.ActionBar$Tab")
								.instance(j));
						secondText.click();
						assertFalse(netError.exists());
					}
				}
				if (backImage.exists()) {
					backImage.click();
				} else if(!backImage.exists()){
					UiDevice.getInstance().pressBack();
				}

			}
			System.out.println("分类 列表：" + fenleiList);

		}
	}

}
