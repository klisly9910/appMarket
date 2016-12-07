package com.testcases;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Campaign extends UiAutomatorTestCase {
	UiObject backImage = new UiObject(
			new UiSelector().className("android.widget.ImageButton"));

	public void setUp() throws UiObjectNotFoundException {
		new UiObject(new UiSelector().text("机锋市场")).click();
	}

	public void tearDown() throws UiObjectNotFoundException {
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}

	public void testCampaign() throws UiObjectNotFoundException {
		UiObject campaign = new UiObject(new UiSelector().text("活动"));
		campaign.click();
		UiCollection campaignList = new UiCollection(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/gm3_home_campaign_fragment_list"));
		if (campaignList.exists()) {
			int count = campaignList.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/gm3_campaign_item_iv_logo"));
			for (int i = 0; i < count; i++) {
				UiObject campaignLogo = new UiObject(new UiSelector()
						.resourceId(
								"com.mappn.gfan:id/gm3_campaign_item_iv_logo")
						.instance(i));
				campaignLogo.click();
				UiObject detailCampaign = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/gm3_campaign_detail_iv_logo"));
				assertTrue(detailCampaign.exists());
				backImage.click();

			}
			backImage.click();

		}
	}


}
