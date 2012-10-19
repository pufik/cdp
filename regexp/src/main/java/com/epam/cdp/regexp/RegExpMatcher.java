package com.epam.cdp.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpMatcher {
	private final static String MOBILE_REGEXP = "[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}";
	private final static String EMAIL_REGEXP = "[[a-z|A-Z|0-9\\W|_]&&\\S]+?@[[a-z|0-9\\W]&&\\S]+?\\.[a-z]+?";
	private final static String CREDIT_CARD_REGEXP = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
	private final static String CAR_NUMBER_REGEXP = "[A-Z]{2}\\d{4}[A-Z]{2}";
	private final static String PASSPORT_REGEXP = "[A-Z]{2}\\d{6}";
	private final Pattern mobilePattern;
	private final Pattern emailPattern;
	private final Pattern creditCardPattern;
	private final Pattern carPattern;
	private final Pattern passportPattern;

	public RegExpMatcher() {
		mobilePattern = Pattern.compile(MOBILE_REGEXP);
		emailPattern = Pattern.compile(EMAIL_REGEXP);
		creditCardPattern = Pattern.compile(CREDIT_CARD_REGEXP);
		carPattern = Pattern.compile(CAR_NUMBER_REGEXP);
		passportPattern = Pattern.compile(PASSPORT_REGEXP);
	}

	public boolean checkMobilePhone(String phoneNumber) {
		return checkMatch(mobilePattern, phoneNumber);
	}

	public boolean checkEmail(String email) {
		return checkMatch(emailPattern, email);
	}

	public boolean checkCreditCard(String cardNumber) {
		return checkMatch(creditCardPattern, cardNumber);
	}

	public boolean checkCarNumber(String carNumber) {
		return checkMatch(carPattern, carNumber);
	}

	public boolean checkPassport(String passport) {
		return checkMatch(passportPattern, passport);
	}

	private boolean checkMatch(Pattern pattern, String toMatch) {
		Matcher matcher = pattern.matcher(toMatch);

		return matcher.matches();
	}
}
