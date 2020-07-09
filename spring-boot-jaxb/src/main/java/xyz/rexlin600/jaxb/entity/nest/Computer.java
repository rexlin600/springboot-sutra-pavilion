package xyz.rexlin600.jaxb.entity.nest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 计算机类
 *
 * @author: hekunlin
 * @since: 2020/3/6
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "COMPUTER")
public class Computer implements Serializable {

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
     * CPU
     */
    @XmlElement(name = "CPU")
    private Cpu cpu;

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
    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }
}