package xyz.rexlin600.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * User
 *
 * @author hekunlin
 */
@Document(indexName = "rexlin600-user", type = "user", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	/**
	 * Id
	 */
	@Id
	private Long id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Age
	 */
	private Integer age;
	/**
	 * Remark
	 */
	private String remark;

	/**
	 * To string string
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", remark=" + remark + "]";
	}
}