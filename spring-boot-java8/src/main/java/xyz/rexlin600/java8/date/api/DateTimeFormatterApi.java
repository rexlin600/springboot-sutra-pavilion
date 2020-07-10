package xyz.rexlin600.java8.date.api;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Date time formatter api
 *
 * @author hekunlin
 */
@Slf4j
public class DateTimeFormatterApi {

	/**
	 * Format
	 */
	public static void format() {
		String format0 = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		String format1 = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
		String format2 = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		String format3 = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

		String format4 = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);
		String format5 = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
		String format6 = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		String format7 = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String format8 = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);


		String format9 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		String format10 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String format11 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		String format12 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
		String format13 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH));
		String format14 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.JAPAN));

		log.info("==> format0 is [{}]", format0);
		log.info("==> format1 is [{}]", format1);
		log.info("==> format2 is [{}]", format2);
		log.info("==> format3 is [{}]", format3);
		log.info("==> format4 is [{}]", format4);
		log.info("==> format5 is [{}]", format5);
		log.info("==> format6 is [{}]", format6);
		log.info("==> format7 is [{}]", format7);
		log.info("==> format8 is [{}]", format8);
		log.info("==> format9 is [{}]", format9);
		log.info("==> format10 is [{}]", format10);
		log.info("==> format11 is [{}]", format11);
		log.info("==> format12 is [{}]", format12);
		log.info("==> format13 is [{}]", format13);
		log.info("==> format14 is [{}]", format14);

	}

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		format();
	}

}