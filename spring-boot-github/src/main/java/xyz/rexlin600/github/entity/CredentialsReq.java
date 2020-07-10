package xyz.rexlin600.github.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Credentials req
 *
 * @author hekunlin
 */
@Data
public class CredentialsReq implements Serializable {

	/**
	 * Username
	 */
	private String username;

	/**
	 * Password
	 */
	private String password;

}