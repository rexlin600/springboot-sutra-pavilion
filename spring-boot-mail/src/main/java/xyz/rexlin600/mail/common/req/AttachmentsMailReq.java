package xyz.rexlin600.mail.common.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Attachments mail req
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttachmentsMailReq implements Serializable {

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
	 * File path
	 */
	private String filePath;

}
