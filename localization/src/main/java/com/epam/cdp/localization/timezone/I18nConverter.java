package com.epam.cdp.localization.timezone;

import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class I18nConverter {
	public int convertTime(TimeZone timeZone) {
		Calendar calendar = new GregorianCalendar(timeZone);

		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public void convertCurrency(Locale locale){
		Currency currency = Currency.getInstance(locale); 
	}
}
