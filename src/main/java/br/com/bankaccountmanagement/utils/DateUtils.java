package br.com.bankaccountmanagement.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: Kleryton de souza
 */
public class DateUtils {
	
	public static LocalDate convertStringToLocalDate(String date) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		return localDate;
	}
	
	public static String convertLocalDateToString(LocalDate date) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String localDateString = date.format(dateFormatter);
		return localDateString;
	}

}
