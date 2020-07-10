package xyz.rexlin600.aop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * Sys log
 *
 * @author hekunlin
 */
@TableName("sys_log")
public class SysLog extends Model {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	private Long id;

	/**
	 * Type
	 */
	private String type;

	/**
	 * Title
	 */
	private String title;

	/**
	 * Create by
	 */
	private String createBy;

	/**
	 * Create time
	 */
	private LocalDateTime createTime;

	/**
	 * Update time
	 */
	private LocalDateTime updateTime;

	/**
	 * Remote addr
	 */
	private String remoteAddr;

	/**
	 * User agent
	 */
	private String userAgent;

	/**
	 * Request uri
	 */
	private String requestUri;

	/**
	 * Method
	 */
	private String method;

	/**
	 * Params
	 */
	private String params;

	/**
	 * Time
	 */
	private Long time;

	/**
	 * Exception
	 */
	private String exception;

	/**
	 * Service id
	 */
	private String serviceId;

	/**
	 * Del flag
	 */
	private String delFlag;


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
	 * Gets type *
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets type *
	 *
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets title *
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title *
	 *
	 * @param title title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets create by *
	 *
	 * @return the create by
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * Sets create by *
	 *
	 * @param createBy create by
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * Gets create time *
	 *
	 * @return the create time
	 */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	/**
	 * Sets create time *
	 *
	 * @param createTime create time
	 */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	/**
	 * Gets update time *
	 *
	 * @return the update time
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets update time *
	 *
	 * @param updateTime update time
	 */
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Gets remote addr *
	 *
	 * @return the remote addr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * Sets remote addr *
	 *
	 * @param remoteAddr remote addr
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * Gets user agent *
	 *
	 * @return the user agent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Sets user agent *
	 *
	 * @param userAgent user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * Gets request uri *
	 *
	 * @return the request uri
	 */
	public String getRequestUri() {
		return requestUri;
	}

	/**
	 * Sets request uri *
	 *
	 * @param requestUri request uri
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	/**
	 * Gets method *
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets method *
	 *
	 * @param method method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Gets params *
	 *
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * Sets params *
	 *
	 * @param params params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * Gets time *
	 *
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * Sets time *
	 *
	 * @param time time
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	/**
	 * Gets exception *
	 *
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * Sets exception *
	 *
	 * @param exception exception
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * Gets service id *
	 *
	 * @return the service id
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * Sets service id *
	 *
	 * @param serviceId service id
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * Gets del flag *
	 *
	 * @return the del flag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * Sets del flag *
	 *
	 * @param delFlag del flag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * To string string
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SysLog{" +
				"id=" + id +
				", type=" + type +
				", title=" + title +
				", createBy=" + createBy +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", remoteAddr=" + remoteAddr +
				", userAgent=" + userAgent +
				", requestUri=" + requestUri +
				", method=" + method +
				", params=" + params +
				", time=" + time +
				", exception=" + exception +
				", serviceId=" + serviceId +
				", delFlag=" + delFlag +
				"}";
	}
}
