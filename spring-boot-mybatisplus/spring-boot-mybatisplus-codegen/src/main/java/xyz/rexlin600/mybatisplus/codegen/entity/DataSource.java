package xyz.rexlin600.mybatisplus.codegen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Data source
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DataSource implements Serializable {

	/**
	 * Id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * Host
	 */
	private String host;

	/**
	 * Port
	 */
	private String port;

	/**
	 * Username
	 */
	private String username;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Db name
	 */
	private String dbName;

	/**
	 * Create time
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * Update time
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

}