package xyz.rexlin600.redis.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Blog
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Blog implements Serializable {

	/**
	 * Id
	 */
	@NotNull
	private Long id;

	/**
	 * Title
	 */
	@NotEmpty
	private String title;

	/**
	 * Content
	 */
	@NotEmpty
	private String content;

	/**
	 * Popular
	 */
	@NotNull
	private boolean popular;

	/**
	 * Create date
	 */
	private String createDate;

}