package xyz.rexlin600.helloworld.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ali app
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliApp {

	/**
	 * App id
	 */
	private String appId;

	/**
	 * Private key
	 */
	private String privateKey;

	/**
	 * Ali public key
	 */
	private String aliPublicKey;

	/**
	 * Aes key
	 */
	private String aesKey;

}