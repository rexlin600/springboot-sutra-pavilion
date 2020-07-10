package xyz.rexlin600.helloworld.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Ex dto
 *
 * @param <T> parameter
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExDTO<T> implements Serializable {

	/**
	 * Msg
	 */
	private String msg;

	/**
	 * Code
	 */
	private Integer code;

	/**
	 * Data
	 */
	private T data;

	/**
	 * Url
	 */
	private String url;

}