package com.blogspot.notes.automation.qa.core.pages;

import com.blogspot.notes.automation.qa.core.model.PaymentsEntity;
import ru.yandex.qatools.allure.annotations.Step;

import javax.money.MonetaryAmount;

import static com.blogspot.notes.automation.qa.core.utils.CurrencyUtils.moneyOf;

public class NewPaymentPage {

	@Step("Select payment profile = {0}.")
	public NewPaymentPage selectPaymentProfile(final String name) {
		return this;
	}

	public NewPaymentPage fillInPaymentInfoAndSave(final PaymentsEntity payment) {
		return typeAccountName(payment.getAccount())
				.typeAmount(payment.getFormattedAmount())
				.savePayment();
	}

	@Step("Type account name = {0}.")
	public NewPaymentPage typeAccountName(final String name) {
		return this;
	}

	@Step("Type amount = {0}.")
	public NewPaymentPage typeAmount(final MonetaryAmount amount) {
		return this;
	}

	@Step("Click \"Save\" button.")
	public NewPaymentPage savePayment() {
		return this;
	}

	@Step("Click \"Add new payment\" button.")
	public NewPaymentPage addNewPayment() {
		return this;
	}

	@Step("Click \"Edit payment\" button.")
	public NewPaymentPage editPayment() {
		return this;
	}

	public MonetaryAmount getActualAmount() {
		return moneyOf("£1098.23");
	}
}
