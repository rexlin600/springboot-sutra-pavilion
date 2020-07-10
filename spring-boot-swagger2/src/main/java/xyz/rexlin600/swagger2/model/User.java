package xyz.rexlin600.swagger2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	/**
	 * Id
	 */
	private Long id;

	/**
	 * Name
	 */
	private String name;

	/**
	 * Age
	 */
	private int age;

}
