package xyz.rexlin600.jaxb.entity.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @author: hekunlin
 * @date: 2020/3/6
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "PRODUCT")
public class Product implements Serializable {

    /**
     * ID
     */
    @XmlElement(name = "ID")
    private Long id;

    /**
     * NAME
     */
    @XmlElement(name = "NAME")
    private String name;

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}