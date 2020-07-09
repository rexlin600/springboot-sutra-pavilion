package xyz.rexlin600.common.util;


import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 版本工具类
 *
 * @author rexlin600
 */
public class VersionUtils {

    public VersionUtils() {
    }

    /**
     * 版本比较
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return {@link Integer}
     */
    public static int compareVersion(String version1, String version2) {
        Assert.isTrue(!StringUtils.isEmpty(version1) && !StringUtils.isEmpty(version2), "Error: CompareVersion Error: Illegal Argument !");
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);

        int diff;
        for (diff = 0; idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0 && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0; ++idx) {
        }

        diff = diff != 0 ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 版本比较
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return {@link Boolean}
     */
    public static boolean compare(String version1, String version2) {
        int num = compareVersion(version1, version2);
        return num >= 0;
    }

}
