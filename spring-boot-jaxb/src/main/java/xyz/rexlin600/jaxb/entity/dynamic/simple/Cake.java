package xyz.rexlin600.jaxb.entity.dynamic.simple;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Cake
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "CAKE")
public class Cake implements Serializable {

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