package xyz.rexlin600.rabbitmq.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Message
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Message implements Serializable {

	/**
	 * Id
	 */
	private Long id;

	/**
	 * Content
	 */
	private String content;

}