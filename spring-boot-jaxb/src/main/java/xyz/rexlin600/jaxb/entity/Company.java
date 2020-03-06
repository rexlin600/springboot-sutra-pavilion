package xyz.rexlin600.jaxb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "COMPANY")
public class Company implements Serializable {

    /**
     * 名称
     */
    @XmlElement(name = "NAME")
    private String name;

    /**
     * 地址
     */
    @XmlElement(name = "ADDRESS")
    private String address;

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}