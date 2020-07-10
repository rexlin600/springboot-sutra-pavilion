package xyz.rexlin600.aop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * User
 *
 * @author hekunlin
 */
@TableName("user")
public class User extends Model {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * User name
	 */
	private String userName;

	/**
	 * Age
	 */
	private Integer age;

	/**
	 * Gender
	 */
	private String gender;


	/**
	 * Gets id *
	 *
	 * @return the id
	 */
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
	 * Gets user name *
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets user name *
	 *
	 * @param userName user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets age *
	 *
	 * @return the age
	 */
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
	 * Gets gender *
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets gender *
	 *
	 * @param gender gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * To string string
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName=" + userName +
				", age=" + age +
				", gender=" + gender +
				"}";
	}
}
