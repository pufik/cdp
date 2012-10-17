package com.epam.cdp.localization.converter;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class I18nConverter {
	public int convertTime(TimeZone timeZone) {
		Calendar calendar = new GregorianCalendar(timeZone);

		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public String convertCurrency(Locale locale, Double number) {
		NumberFormat formater = NumberFormat.getCurrencyInstance(locale);
		String result = formater.format(number);

		return result;
	}
}
