package xyz.rexlin600.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Send msg
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMsg implements Serializable {

	/**
	 * List
	 */
	private List<String> list;

	/**
	 * Content
	 */
	private String content;

}