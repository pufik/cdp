package com.epam.cdp.localization;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import com.epam.cdp.localization.timezone.I18nConverter;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("-------Task #1---------");
		l10n("ua", "UA");
		
		System.out.println("-------Task #2---------");
		Collection<TimeZone> zones = Arrays.asList(new TimeZone[] {
				TimeZone.getTimeZone("PST"),
				TimeZone.getTimeZone("America/New_York"),
				TimeZone.getTimeZone("Europe/London"),
				TimeZone.getTimeZone("Asia/Tokyo")
		});
		
		timeConverter(zones);
		
		System.out.println("-------Task #3---------");
	}

	private static void l10n(String language, String country) {
		Locale locale = new Locale(language, country);
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
}
