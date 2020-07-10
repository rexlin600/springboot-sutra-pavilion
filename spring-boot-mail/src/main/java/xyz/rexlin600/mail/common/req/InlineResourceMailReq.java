package xyz.rexlin600.mail.common.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Inline resource mail req
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InlineResourceMailReq implements Serializable {

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

	/**
	 * Rsc path
	 */
	private String rscPath;

	/**
	 * Rsc id
	 */
	private String rscId;

}
