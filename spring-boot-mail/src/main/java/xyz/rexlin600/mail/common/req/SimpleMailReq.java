package xyz.rexlin600.mail.common.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Simple mail req
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleMailReq implements Serializable {

	/**
	 * To
	 */
	private String to;

	/**
	 * Subject
	 */
	private String subject;

	/**
	 * Content
	 */
	private String content;

}