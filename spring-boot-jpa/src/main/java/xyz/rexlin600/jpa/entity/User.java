package xyz.rexlin600.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User
 *
 * @author hekunlin
 */
@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "user")
public class User {

	/**
	 * Id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
	@GenericGenerator(name = "myid", strategy = "xyz.rexlin600.helloworld.config.ManulInsertGenerator")
	private Long id;

	/**
	 * Name
	 */
	@Column(nullable = false, unique = true)
	private String name;

	/**
	 * Age
	 */
	@Column(nullable = false, unique = false)
	private Integer age;

	/**
	 * Email
	 */
	@Column(nullable = false, unique = true)
	private String email;

}