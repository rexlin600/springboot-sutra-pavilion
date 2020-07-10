package xyz.rexlin600.helloworld.entity.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Post req
 *
 * @author hekunlin
 */
@Data
public class PostReq implements Serializable {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Age
	 */
	private Integer age;

}