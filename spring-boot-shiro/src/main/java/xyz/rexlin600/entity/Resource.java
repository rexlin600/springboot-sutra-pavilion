package xyz.rexlin600.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Resource 实体类
 *
 * @author: rexlin600
 * @date: 2020-01-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resource implements Serializable {

    /**
     * 资源ID
     */
    private Long id;

    /**
     * 父资源ID
     */
    private Long parentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
     * <p>
     * 1 菜单 Menu
     * 2 页面 Page
     * 3 按钮 Button
     */
    private Integer type;

    /**
     * 资源类型名称
     * <p>
     * 1 菜单 Menu
     * 2 页面 Page
     * 3 按钮 Button
     */
    private String typeName;

    /**
     * 资源序号
     */
    private Integer sort;

    /**
     * 资源icon
     */
    private String icon;

    // -----------------------------------------------------------------------------------------------
    // 如果是按钮资源还有如应当具有如下 filed
    // -----------------------------------------------------------------------------------------------
    /**
     * http 请求方法
     */
    private String httpMethod;

    /**
     * 请求地址
     */
    private String httpUrl;


}