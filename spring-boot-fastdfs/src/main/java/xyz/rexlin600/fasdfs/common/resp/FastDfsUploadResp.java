package xyz.rexlin600.fasdfs.common.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * Fast dfs upload resp
 *
 * @author hekunlin
 */
@Data
public class FastDfsUploadResp implements Serializable {

	/**
	 * Relative path
	 */
	private String relativePath;

	/**
	 * Full path
	 */
	private String fullPath;

}