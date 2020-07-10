package xyz.rexlin600.jaxb.entity.list;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Document
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "DOCUMENT")
public class Document implements Serializable {

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
	@XmlElement(name = "FileType")
	private List<FileType> list;

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
	public List<FileType> getList() {
		return list;
	}

	/**
	 * Sets list *
	 *
	 * @param list list
	 */
	public void setList(List<FileType> list) {
		this.list = list;
	}
}