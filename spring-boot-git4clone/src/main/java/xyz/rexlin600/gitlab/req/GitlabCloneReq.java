package xyz.rexlin600.gitlab.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Gitlab clone req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitlabCloneReq implements Serializable {

	/**
	 * Name
	 */
	private String name;

	/**
	 * Dir
	 */
	private String dir;

	/**
	 * Owner
	 */
	private String owner;

	/**
	 * Namespace name
	 */
	private String namespaceName;

}