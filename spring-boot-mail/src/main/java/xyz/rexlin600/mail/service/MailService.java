package xyz.rexlin600.mail.service;

/**
 * Mail service
 *
 * @author hekunlin
 */
public interface MailService {

	/**
	 * Send simple mail *
	 *
	 * @param to      to
	 * @param subject subject
	 * @param content content
	 */
	void sendSimpleMail(String to, String subject, String content);

	/**
	 * Send html mail *
	 *
	 * @param to      to
	 * @param subject subject
	 * @param content content
	 */
	void sendHtmlMail(String to, String subject, String content);

	/**
	 * Send attachments mail *
	 *
	 * @param to       to
	 * @param subject  subject
	 * @param content  content
	 * @param filePath file path
	 */
	void sendAttachmentsMail(String to, String subject, String content, String filePath);

	/**
	 * Send inline resource mail *
	 *
	 * @param to      to
	 * @param subject subject
	 * @param content content
	 * @param rscPath rsc path
	 * @param rscId   rsc id
	 */
	void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}