package xyz.rexlin600.jdbc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * User
 *
 * @author hekunlin
 */
@ToString
@Data
@Builder
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
	private Integer age;
	/**
	 * Email
	 */
	private String email;
}