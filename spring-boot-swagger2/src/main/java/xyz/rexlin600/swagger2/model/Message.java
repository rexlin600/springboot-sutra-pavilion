package xyz.rexlin600.swagger2.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Message
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

	/**
	 * Id
	 */
	private Long id;

	/**
	 * Text
	 */
	@ApiModelProperty(value = "消息体")
	private String text;

	/**
	 * Summary
	 */
	@ApiModelProperty(value = "消息总结")
	private String summary;

	/**
	 * Create date
	 */
	private Date createDate;

	/**
	 * To string string
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", text='" + text + '\'' +
				", summary='" + summary + '\'' +
				", createDate=" + createDate +
				'}';
	}
}
