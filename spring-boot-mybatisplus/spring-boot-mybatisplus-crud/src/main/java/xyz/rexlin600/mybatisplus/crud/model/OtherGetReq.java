package xyz.rexlin600.mybatisplus.crud.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Other get req
 *
 * @author hekunlin
 */
@ToString
@Data
public class OtherGetReq implements Serializable {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Age
	 */
	private Integer age;

	/**
	 * Address
	 */
	private String address;

}