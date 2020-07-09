package xyz.rexlin600.jaxb.entity.simple;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 用户实体类
 *
 * @author: hekunlin
 * @since: 2020/3/2
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "USER")
public class User {

    /**
     * ID
     */
    @XmlElement(name = "ID", type = Long.class)
    private Long id;

    /**
     * 编码
     */
    @XmlElement(name = "NUMBER", type = Long.class)
    private Long number;

    /**
     * 姓名
     */
    @XmlElement(name = "NAME", type = String.class)
    private String name;

    /**
     * 生日
     */
    @XmlElement(name = "BIRTHDAY")
    private String birthday;

    /**
     * 是否为信徒
     * 是-true
     * 否-false
     */
    @XmlElement(name = "FOLLOWER", type = Boolean.class)
    private Boolean follower;

    /**
     * 年龄
     */
    @XmlElement(name = "AGE", type = Integer.class)
    private Integer age;

    /**
     * 存款
     */
    @XmlElement(name = "DEPOSIT", type = Double.class)
    private Double deposit;

    // -----------------------------------------------------------------------------------------------
    // GETTER SETTER
    // -----------------------------------------------------------------------------------------------

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @XmlTransient
    public Boolean getFollower() {
        return follower;
    }

    public void setFollower(Boolean follower) {
        this.follower = follower;
    }

    @XmlTransient
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlTransient
    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
}