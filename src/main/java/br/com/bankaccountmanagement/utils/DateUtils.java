package br.com.bankaccountmanagement.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	

	public static LocalDate convertStringToLocalDate(String date) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		return localDate;
	}

}
