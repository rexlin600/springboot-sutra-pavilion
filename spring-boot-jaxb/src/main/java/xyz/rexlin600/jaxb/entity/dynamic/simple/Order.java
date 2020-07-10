package xyz.rexlin600.jaxb.entity.dynamic.simple;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Order
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "PRODUCT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

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
	 * List
	 */
	@XmlElementWrapper(name = "PRODUCT")
	@XmlAnyElement
	private List<Object> list;

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
	 * Gets list *
	 *
	 * @return the list
	 */
	@XmlTransient
	public List<Object> getList() {
		return list;
	}

	/**
	 * Sets list *
	 *
	 * @param list list
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}
}