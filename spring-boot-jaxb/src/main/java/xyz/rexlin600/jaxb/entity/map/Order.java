package xyz.rexlin600.jaxb.entity.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.Map;

/**
 * Order
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

	/**
	 * Properties
	 */
	@XmlAnyAttribute
	private Map<QName, String> properties;

	/**
	 * Product
	 */
	@XmlElement(name = "PRODUCT")
	private Product product;

	/**
	 * Gets properties *
	 *
	 * @return the properties
	 */
	@XmlTransient
	public Map<QName, String> getProperties() {
		return properties;
	}

	/**
	 * Sets properties *
	 *
	 * @param properties properties
	 */
	public void setProperties(Map<QName, String> properties) {
		this.properties = properties;
	}

	/**
	 * Gets product *
	 *
	 * @return the product
	 */
	@XmlTransient
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets product *
	 *
	 * @param product product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}