package xyz.rexlin600.swagger2.repository;

import org.springframework.stereotype.Service;
import xyz.rexlin600.swagger2.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In memory message repository
 *
 * @author hekunlin
 */
@Service("messageRepository")
public class InMemoryMessageRepository implements MessageRepository {

	/**
	 * counter
	 */
	private static AtomicLong counter = new AtomicLong();
	/**
	 * Messages
	 */
	private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<>();

	/**
	 * Find all list
	 *
	 * @return the list
	 */
	@Override
	public List<Message> findAll() {
		List<Message> messages = new ArrayList<Message>(this.messages.values());
		return messages;
	}

	/**
	 * Save message
	 *
	 * @param message message
	 * @return the message
	 */
	@Override
	public Message save(Message message) {
		Long id = message.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			message.setId(id);
		}
		this.messages.put(id, message);
		return message;
	}

	/**
	 * Update message
	 *
	 * @param message message
	 * @return the message
	 */
	@Override
	public Message update(Message message) {
		this.messages.put(message.getId(), message);
		return message;
	}

	/**
	 * Update text message
	 *
	 * @param message message
	 * @return the message
	 */
	@Override
	public Message updateText(Message message) {
		Message msg = this.messages.get(message.getId());
		msg.setText(message.getText());
		this.messages.put(msg.getId(), msg);
		return msg;
	}

	/**
	 * Find message message
	 *
	 * @param id id
	 * @return the message
	 */
	@Override
	public Message findMessage(Long id) {
		return this.messages.get(id);
	}

	/**
	 * Delete message *
	 *
	 * @param id id
	 */
	@Override
	public void deleteMessage(Long id) {
		this.messages.remove(id);
	}

}
