package com.blogspot.notes.automation.qa.tests;

import com.blogspot.notes.automation.qa.core.annotations.Entity;
import com.blogspot.notes.automation.qa.core.core.BaseTest;
import com.blogspot.notes.automation.qa.core.db.utils.DataProviderUtils;

import static com.blogspot.notes.automation.qa.core.db.utils.DataProviderUtils.*;
import static com.blogspot.notes.automation.qa.core.pages.PageObjectSupplier.*;

import com.blogspot.notes.automation.qa.core.enums.Schema;

import com.blogspot.notes.automation.qa.core.enums.URL;
import com.blogspot.notes.automation.qa.core.model.CreatePaymentDataEntity;
import com.blogspot.notes.automation.qa.core.model.EditPaymentDataEntity;
import com.blogspot.notes.automation.qa.core.model.LoginWithValidCredentialsData;
import com.blogspot.notes.automation.qa.core.pages.HomePage;
import com.blogspot.notes.automation.qa.core.pages.NewPaymentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataProviderTests extends BaseTest {

	@Entity(model = CreatePaymentDataEntity.class, schema = Schema.AUTOMATION)
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
	public void createPayment(final CreatePaymentDataEntity data) {
		loadSiteUrl(URL.DEV)
				.loginWith(data.getUser())
				.openNewPaymentsPage()
				.selectPaymentProfile(data.getProfile().getName())
				.addNewPayment()
				.fillInPaymentInfoAndSave(data.getPayment());

		Assert.assertEquals($(NewPaymentPage.class).getActualAmount(),
				data.getPayment().getFormattedAmount());
	}

	@Entity(model = EditPaymentDataEntity.class, schema = Schema.AUTOMATION)
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
	public void editPayment(final EditPaymentDataEntity data) {
		loadSiteUrl(URL.DEV)
				.loginWith(data.getUser())
				.openNewPaymentsPage()
				.selectPaymentProfile(data.getProfile().getName())
				.addNewPayment()
				.fillInPaymentInfoAndSave(data.getFirstPayment())
				.editPayment()
				.fillInPaymentInfoAndSave(data.getSecondPayment());

		Assert.assertEquals($(NewPaymentPage.class).getActualAmount(),
				data.getSecondPayment().getFormattedAmount());
	}

	@Entity(model = LoginWithValidCredentialsData.class, schema = Schema.AUTOMATION)
	@Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
	public void loginWithValidCredentials(final LoginWithValidCredentialsData data) {
		loadSiteUrl(URL.DEV)
				.loginWith(data.getUser());

		Assert.assertEquals($(HomePage.class).getActualUsername(),
				data.getUser().getUsername());
	}
}
