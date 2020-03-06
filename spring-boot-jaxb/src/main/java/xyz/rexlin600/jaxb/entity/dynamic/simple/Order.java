package xyz.rexlin600.jaxb.entity.dynamic.simple;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * 产品类
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "PRODUCT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

    /**
     * ID
     */
    @XmlElement(name = "ID")
    private Long id;

    /**
     * 名称
     */
    @XmlElement(name = "NAME")
    private String name;

    /**
     * 列表
     */
    @XmlElementWrapper(name = "PRODUCT")
    @XmlAnyElement
    private List<Object> list;

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

    @XmlTransient
    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}