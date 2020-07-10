package xyz.rexlin600.oss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * Oss type enum
 *
 * @author hekunlin
 */
@Getter
@AllArgsConstructor
public enum OSSTypeEnum {

	/**
	 * Ali oss type enum
	 */
	ALI(1, "阿里云", "阿里云 OSS 文件上传"),
	/**
	 * Tx oss type enum
	 */
	TX(2, "腾讯云", "腾讯云 OSS 文件上传"),
	/**
	 * Qn oss type enum
	 */
	QN(3, "七牛云", "七牛云 OSS 文件上传");

	/**
	 * map
	 */
	private static volatile HashMap<Integer, OSSTypeEnum> map;

	/**
	 * Code
	 */
	private Integer code;

	/**
	 * Name
	 */
	private String name;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Gets map *
	 *
	 * @return the map
	 */
	public static HashMap<Integer, OSSTypeEnum> getMap() {
		if (map == null) {
			synchronized (OSSTypeEnum.class) {
				if (map == null) {
					map = new HashMap<>();
					for (OSSTypeEnum v : OSSTypeEnum.values()) {
						map.put(v.code, v);
					}
				}
			}
		}
		return map;
	}

	/**
	 * Gets name by code *
	 *
	 * @param code code
	 * @return the name by code
	 */
	public static String getNameByCode(Integer code) {
		String name = null;
		OSSTypeEnum v = getMap().get(code);
		if (v != null) {
			name = v.getName();
		}
		return name;
	}

	/**
	 * Get oss type enum
	 *
	 * @param step step
	 * @return the oss type enum
	 */
	public static OSSTypeEnum get(Integer step) {
		return getMap().get(step);
	}

}
