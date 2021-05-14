package xyz.rexlin600.common.util;

import xyz.rexlin600.common.constant.StringPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Url params util
 *
 * @author hekunlin
 * @since 2020-08-07
 */
public class UrlParamsUtil {

	private static final Integer TWO = 2;

	/**
	 * 将Map转成String, 可以指定分隔符
	 *
	 * @param map       map
	 * @param separator 分隔符
	 * @return res string
	 */
	public static String join(Map<String, String> map, String separator) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			builder.append(entry.getKey()).append("=").append(entry.getValue()).append(separator);
		}
		if (builder.length() > 0) {
			builder.delete(builder.length() - separator.length(), builder.length());
		}
		return builder.toString();
	}

	/**
	 * 解析ulr参数为map
	 *
	 * @param paramsPath url
	 * @return map map
	 */
	public static Map<String, String> split(String paramsPath) {
		return split(paramsPath, "=");
	}

	/**
	 * 解析ulr参数为map
	 *
	 * @param paramsPath url
	 * @param separator  separator
	 * @return map map
	 */
	public static Map<String, String> split(String paramsPath, String separator) {
		if (paramsPath == null || paramsPath.length() == 0 || !paramsPath.contains(StringPool.QUESTION_MARK)
				|| !paramsPath.contains(separator)) {
			return null;
		}
		String[] paramsArr = paramsPath.split("\\?");
		String paramsStr = paramsArr[paramsArr.length - 1];
		String[] paramsItems = paramsStr.split(StringPool.AMPERSAND);
		if (paramsItems.length == 0) {
			return null;
		}
		Map<String, String> result = new HashMap<>(paramsItems.length);
		for (String item : paramsItems) {
			if (item == null || item.length() == 0 || !item.contains(separator)) {
				continue;
			}
			String[] keyValue = item.split(separator);
			result.put(keyValue[0], keyValue[1]);
		}
		if (result.isEmpty()) {
			return null;
		}
		return result;
	}

	/**
	 * 将keyValues转成Map
	 *
	 * @param keyValues kayValue
	 * @return map map
	 */
	public static Map<String, String> build(String... keyValues) {
		if (keyValues == null || keyValues.length <= 0) {
			return null;
		}
		Map<String, String> result = new HashMap<>(keyValues.length);
		for (int i = 0; i < keyValues.length; i += TWO) {
			result.put(keyValues[i], keyValues[i + 1]);
		}
		return result;
	}

	/**
	 * 在原Map添加keyValues
	 *
	 * @param originMap origin Map
	 * @param keyValues keyValues
	 */
	public static void add(Map<String, String> originMap, String... keyValues) {
		if (originMap == null || originMap.size() == 0) {
			return;
		}
		for (int i = 0; i < keyValues.length; i += TWO) {
			originMap.put(keyValues[i], keyValues[i + 1]);
		}
	}

}
