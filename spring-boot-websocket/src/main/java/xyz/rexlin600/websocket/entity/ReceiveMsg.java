package xyz.rexlin600.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Receive msg
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveMsg implements Serializable {

	/**
	 * Name list
	 */
	private List<String> nameList;

	/**
	 * Content
	 */
	private String content;

}