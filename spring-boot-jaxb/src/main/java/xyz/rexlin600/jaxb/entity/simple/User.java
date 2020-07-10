package xyz.rexlin600.jaxb.entity.simple;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * User
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "USER")
public class User {

	/**
	 * Id
	 */
	@XmlElement(name = "ID", type = Long.class)
	private Long id;

	/**
	 * Number
	 */
	@XmlElement(name = "NUMBER", type = Long.class)
	private Long number;

	/**
	 * Name
	 */
	@XmlElement(name = "NAME", type = String.class)
	private String name;

	/**
	 * Birthday
	 */
	@XmlElement(name = "BIRTHDAY")
	private String birthday;

	/**
	 * Follower
	 */
	@XmlElement(name = "FOLLOWER", type = Boolean.class)
	private Boolean follower;

	/**
	 * Age
	 */
	@XmlElement(name = "AGE", type = Integer.class)
	private Integer age;

	/**
	 * Deposit
	 */
	@XmlElement(name = "DEPOSIT", type = Double.class)
	private Double deposit;

	// -----------------------------------------------------------------------------------------------
	// GETTER SETTER
	// -----------------------------------------------------------------------------------------------

	/**
	 * Gets id *
	 *
	 * @return the id
	 */
	@XmlTransient
	public Long getId() {
		return id;
	}

	/**
	 * Sets id *
	 *
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets number *
	 *
	 * @return the number
	 */
	@XmlTransient
	public Long getNumber() {
		return number;
	}

	/**
	 * Sets number *
	 *
	 * @param number number
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * Gets name *
	 *
	 * @return the name
	 */
	@XmlTransient
	public String getName() {
		return name;
	}

	/**
	 * Sets name *
	 *
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets birthday *
	 *
	 * @return the birthday
	 */
	@XmlTransient
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Sets birthday *
	 *
	 * @param birthday birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets follower *
	 *
	 * @return the follower
	 */
	@XmlTransient
	public Boolean getFollower() {
		return follower;
	}

	/**
	 * Sets follower *
	 *
	 * @param follower follower
	 */
	public void setFollower(Boolean follower) {
		this.follower = follower;
	}

	/**
	 * Gets age *
	 *
	 * @return the age
	 */
	@XmlTransient
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets age *
	 *
	 * @param age age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Gets deposit *
	 *
	 * @return the deposit
	 */
	@XmlTransient
	public Double getDeposit() {
		return deposit;
	}

	/**
	 * Sets deposit *
	 *
	 * @param deposit deposit
	 */
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
}