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
 * 文件类
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "DOCUMENT")
public class Document implements Serializable {

    /**
     * ID
     */
    @XmlElement(name = "ID")
    private Long id;

    /**
     * 文件名称
     */
    @XmlElement(name = "NAME")
    private String name;

    /**
     * 文件类型列表
     */
    @XmlElement(name = "FileType")
    private List<FileType> list;

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
    public List<FileType> getList() {
        return list;
    }

    public void setList(List<FileType> list) {
        this.list = list;
    }
}