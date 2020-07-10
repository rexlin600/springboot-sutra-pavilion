package xyz.rexlin600.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * User
 *
 * @author hekunlin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	/**
	 * Id
	 */
	@Id
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
