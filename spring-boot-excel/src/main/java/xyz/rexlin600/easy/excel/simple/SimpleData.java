package xyz.rexlin600.easy.excel.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Simple data
 *
 * @author hekunlin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SimpleData implements Serializable {

	/**
	 * String
	 */
	private String string;

	/**
	 * Date
	 */
	private Date date;

	/**
	 * Double data
	 */
	private Double doubleData;

}