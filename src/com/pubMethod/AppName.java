package com.pubMethod;

import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class AppName extends UiAutomatorTestCase {
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));
	UiObject netError = new UiObject(
			new UiSelector().resourceId("com.mappn.gfan:id/netErrorView"));

	public void clickApp() throws UiObjectNotFoundException {

		assertFalse(netError.exists());
		UiCollection recyclerView = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/recyclerView"));
		ArrayList<String> appNameList = new ArrayList<String>();
		if (recyclerView.exists()) {
			int count = recyclerView.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/appNameText"));
			for (int i = 0; i < count; i++) {
				UiObject appNameText = new UiObject(new UiSelector()
						.resourceId("com.mappn.gfan:id/appNameText")
						.instance(i));
				appNameList.add(i, appNameText.getText());
				appNameText.click();
				assertFalse(netError.exists());
				backImage.click();

			}
			System.out.println("appName is :" + appNameList);
		}
	}

}
