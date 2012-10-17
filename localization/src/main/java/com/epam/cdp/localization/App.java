package com.epam.cdp.localization;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.epam.cdp.localization.converter.I18nConverter;
import com.epam.cdp.localization.converter.Utf8Control;

public class App {
	private static final String COUNTRY = "US";
	private static final String LANGUAGE = "en";

	public static void main(String[] args) {
		Locale locale = new Locale(LANGUAGE, COUNTRY);

		System.out.println("-------Task #1---------");
		l10n(locale);

		System.out.println("-------Task #2---------");
		Collection<TimeZone> zones = Arrays.asList(new TimeZone[] {
				TimeZone.getTimeZone("PST"),
				TimeZone.getTimeZone("America/New_York"),
				TimeZone.getTimeZone("Europe/London"),
				TimeZone.getTimeZone("Asia/Tokyo") });

		timeConverter(zones);

		System.out.println("-------Task #3---------");
		System.out.println(currencyConverter(locale));
	}

	private static void l10n(Locale locale) {
		ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new Utf8Control());

		System.out.println(messages.getString("main.welcome"));
		System.out.println(messages.getString("main.description"));
	}

	private static void timeConverter(Collection<TimeZone> timezones) {
		I18nConverter converter = new I18nConverter();

		for (TimeZone zone : timezones) {
			System.out.println("Timezone: " + zone.getDisplayName());
			System.out.println("Hour: " + converter.convertTime(zone));
		}
	}

	private static String currencyConverter(Locale locale) {
		I18nConverter converter = new I18nConverter();

		return converter.convertCurrency(locale, 564.0259);
	}
}
