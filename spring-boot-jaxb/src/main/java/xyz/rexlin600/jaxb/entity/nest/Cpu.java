package xyz.rexlin600.jaxb.entity.nest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Cpu
 *
 * @author hekunlin
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "CPU")
public class Cpu implements Serializable {

	/**
	 * Core size
	 */
	@XmlElement(name = "CORESIZE")
	private Integer coreSize;

	/**
	 * Arch
	 */
	@XmlElement(name = "ARCH")
	private String arch;

	/**
	 * Company
	 */
	@XmlElement(name = "COMPANY")
	private Company company;

	/**
	 * Gets core size *
	 *
	 * @return the core size
	 */
	@XmlTransient
	public Integer getCoreSize() {
		return coreSize;
	}

	/**
	 * Sets core size *
	 *
	 * @param coreSize core size
	 */
	public void setCoreSize(Integer coreSize) {
		this.coreSize = coreSize;
	}

	/**
	 * Gets arch *
	 *
	 * @return the arch
	 */
	@XmlTransient
	public String getArch() {
		return arch;
	}

	/**
	 * Sets arch *
	 *
	 * @param arch arch
	 */
	public void setArch(String arch) {
		this.arch = arch;
	}

	/**
	 * Gets company *
	 *
	 * @return the company
	 */
	@XmlTransient
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets company *
	 *
	 * @param company company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
}