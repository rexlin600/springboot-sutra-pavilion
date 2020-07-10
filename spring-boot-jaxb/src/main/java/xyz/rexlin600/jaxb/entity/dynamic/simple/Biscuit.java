package xyz.rexlin600.jaxb.entity.dynamic.simple;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Biscuit
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "BISCUIT")
public class Biscuit implements Serializable {

	/**
	 * Name
	 */
	@XmlElement(name = "NAME")
	private String name;

	/**
	 * Gets name *
	 *
	 * @return the name
	 */
	@XmlTransient
	public String getName() {
		return name;
	}

	/**
	 * Sets name *
	 *
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}
}