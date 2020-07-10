package xyz.rexlin600.transaction.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User
 *
 * @author hekunlin
 */
@Entity
@Data
public class User {

	/**
	 * Id
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Name
	 */
	@Column(nullable = false, length = 20)
	private String name;

	/**
	 * Age
	 */
	@Column(nullable = false)
	private Integer age;

	/**
	 * Email
	 */
	@Column(nullable = false, length = 50)
	private String email;
}