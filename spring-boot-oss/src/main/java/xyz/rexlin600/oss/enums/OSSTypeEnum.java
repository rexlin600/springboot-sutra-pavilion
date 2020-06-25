package xyz.rexlin600.oss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * 云存储类型常量
 *
 * @param
 * @author hekunlin
 * @return
 */
@Getter
@AllArgsConstructor
public enum OSSTypeEnum {

    /**
     * 阿里云
     */
    ALI(1, "阿里云", "阿里云 OSS 文件上传"),
    /**
     * 腾讯云
     */
    TX(2, "腾讯云", "腾讯云 OSS 文件上传"),
    /**
     * 七牛云
     */
    QN(3, "七牛云", "七牛云 OSS 文件上传");

    private static volatile HashMap<Integer, OSSTypeEnum> map;

    /**
     * 编码
     */
    private Integer code;

    /**
     * OSS 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

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
     * 获取名称
     *
     * @param code
     * @return
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
     * 通过编码获取枚举
     *
     * @param step 编码
     * @return
     */
    public static OSSTypeEnum get(Integer step) {
        return getMap().get(step);
    }

}
