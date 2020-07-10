package xyz.rexlin600.jaxb.entity.nest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Company
 *
 * @author hekunlin
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "COMPANY")
public class Company implements Serializable {

	/**
	 * Name
	 */
	@XmlElement(name = "NAME")
	private String name;

	/**
	 * Address
	 */
	@XmlElement(name = "ADDRESS")
	private String address;

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

	/**
	 * Gets address *
	 *
	 * @return the address
	 */
	@XmlTransient
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address *
	 *
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}