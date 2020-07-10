package xyz.rexlin600.mybatisplus.codegen.common.req;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Data source req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DataSourceReq implements Serializable {

	/**
	 * Id
	 */
	private Long id;

	/**
	 * Host
	 */
	@NotEmpty
	private String host;

	/**
	 * Port
	 */
	@NotEmpty
	private String port;

	/**
	 * Username
	 */
	@NotEmpty
	private String username;

	/**
	 * Password
	 */
	@NotEmpty
	private String password;

	/**
	 * Db name
	 */
	@NotEmpty
	private String dbName;

}