package xyz.rexlin600.mybatisplus.crud.model;

import lombok.Data;

/**
 * User
 *
 * @author hekunlin
 */
@Data
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