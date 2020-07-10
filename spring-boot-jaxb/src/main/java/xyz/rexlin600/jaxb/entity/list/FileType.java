package xyz.rexlin600.jaxb.entity.list;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * File type
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "FILETYPE")
public class FileType implements Serializable {

	/**
	 * Id
	 */
	@XmlElement(name = "ID")
	private Long id;

	/**
	 * Type
	 */
	@XmlElement(name = "TYPE")
	private String type;

	/**
	 * Gets id *
	 *
	 * @return the id
	 */
	@XmlTransient
	public Long getId() {
		return id;
	}

	/**
	 * Sets id *
	 *
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets type *
	 *
	 * @return the type
	 */
	@XmlTransient
	public String getType() {
		return type;
	}

	/**
	 * Sets type *
	 *
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
	}
}