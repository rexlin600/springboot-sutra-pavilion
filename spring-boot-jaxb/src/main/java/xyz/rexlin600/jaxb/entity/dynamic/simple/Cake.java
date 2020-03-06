package xyz.rexlin600.jaxb.entity.dynamic.simple;

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
@XmlRootElement(name = "CAKE")
public class Cake implements Serializable {

    /**
     * 名称
     */
    @XmlElement(name = "NAME")
    private String name;

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}