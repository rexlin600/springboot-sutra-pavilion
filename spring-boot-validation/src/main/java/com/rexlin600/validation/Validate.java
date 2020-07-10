package com.rexlin600.validation;

import org.springframework.util.StringUtils;

/**
 * Validate
 *
 * @author hekunlin
 */
public class Validate {

	/**
	 * EIGHT
	 */
	public static final int EIGHT = 8;

	/**
	 * Check params boolean
	 *
	 * @param name    name
	 * @param age     age
	 * @param classes classes
	 * @return the boolean
	 */
	public static boolean checkParams(String name, int age, String classes) {
		if (StringUtils.isEmpty(name)) {
			return false;
		} else if (StringUtils.isEmpty(classes)) {
			return false;
		} else if (age <= EIGHT) {
			return false;
		}

		return true;
	}

}