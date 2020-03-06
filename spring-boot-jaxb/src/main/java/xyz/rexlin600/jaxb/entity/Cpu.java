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
 * 计算机-CPU类
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "CPU")
public class Cpu implements Serializable {

    /**
     * 核心数
     */
    @XmlElement(name = "CORESIZE")
    private Integer coreSize;

    /**
     * CPU架构
     */
    @XmlElement(name = "ARCH")
    private String arch;

    /**
     * 生产厂商
     */
    @XmlElement(name = "COMPANY")
    private Company company;

    @XmlTransient
    public Integer getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    @XmlTransient
    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    @XmlTransient
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}