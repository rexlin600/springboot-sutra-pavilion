package xyz.rexlin600.jaxb.entity.nest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Computer
 *
 * @author hekunlin
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "COMPUTER")
public class Computer implements Serializable {

	/**
	 * Id
	 */
	@XmlElement(name = "ID")
	private Long id;

	/**
	 * Name
	 */
	@XmlElement(name = "NAME")
	private String name;

	/**
	 * Cpu
	 */
	@XmlElement(name = "CPU")
	private Cpu cpu;

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
	 * Gets cpu *
	 *
	 * @return the cpu
	 */
	@XmlTransient
	public Cpu getCpu() {
		return cpu;
	}

	/**
	 * Sets cpu *
	 *
	 * @param cpu cpu
	 */
	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}
}