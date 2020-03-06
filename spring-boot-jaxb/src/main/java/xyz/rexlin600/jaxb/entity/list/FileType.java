package xyz.rexlin600.jaxb.entity.list;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 文件类型类
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "FILETYPE")
public class FileType implements Serializable {

    /**
     * 文件类型ID
     */
    @XmlElement(name = "ID")
    private Long id;

    /**
     * 文件类型
     */
    @XmlElement(name = "TYPE")
    private String type;

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}