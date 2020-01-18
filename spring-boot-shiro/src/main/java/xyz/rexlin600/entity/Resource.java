package xyz.rexlin600.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author rexlin600
 * @since 2020-01-18
 */
@TableName("resource")
public class Resource extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型 1-菜单 2-页面 3-按钮
     */
    private Integer type;

    /**
     * 资源类型名称
     */
    private String typeName;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 资源icon
     */
    private String icon;

    /**
     * HTTP请求方法
     */
    private String httpMethod;

    /**
     * HTTP请求路径
     */
    private String httpUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Override
    public String toString() {
        return "Resource{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", resourceName=" + resourceName +
        ", type=" + type +
        ", typeName=" + typeName +
        ", sort=" + sort +
        ", icon=" + icon +
        ", httpMethod=" + httpMethod +
        ", httpUrl=" + httpUrl +
        "}";
    }
}
