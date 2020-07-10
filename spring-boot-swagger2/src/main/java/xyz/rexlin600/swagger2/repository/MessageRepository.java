
package xyz.rexlin600.swagger2.repository;

import xyz.rexlin600.swagger2.model.Message;

import java.util.List;

/**
 * Message repository
 *
 * @author hekunlin
 */
public interface MessageRepository {

	/**
	 * Find all list
	 *
	 * @return the list
	 */
	List<Message> findAll();

	/**
	 * Save message
	 *
	 * @param message message
	 * @return the message
	 */
	Message save(Message message);

	/**
	 * Update message
	 *
	 * @param message message
	 * @return the message
	 */
	Message update(Message message);

	/**
	 * Update text message
	 *
	 * @param message message
	 * @return the message
	 */
	Message updateText(Message message);

	/**
	 * Find message message
	 *
	 * @param id id
	 * @return the message
	 */
	Message findMessage(Long id);

	/**
	 * Delete message *
	 *
	 * @param id id
	 */
	void deleteMessage(Long id);

}
