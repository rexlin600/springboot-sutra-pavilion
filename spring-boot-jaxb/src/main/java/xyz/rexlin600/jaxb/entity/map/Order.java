package xyz.rexlin600.jaxb.entity.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.io.Serializable;
import java.util.Map;

/**
 * @author: hekunlin
 * @since: 2020/3/6
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

    /**
     * 属性列表
     */
    @XmlAnyAttribute
    private Map<QName, String> properties;

    /**
     * 产品
     */
    @XmlElement(name = "PRODUCT")
    private Product product;

    @XmlTransient
    public Map<QName, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<QName, String> properties) {
        this.properties = properties;
    }

    @XmlTransient
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}