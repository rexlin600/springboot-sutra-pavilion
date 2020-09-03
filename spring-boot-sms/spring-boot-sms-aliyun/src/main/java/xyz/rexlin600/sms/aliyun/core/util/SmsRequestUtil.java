package xyz.rexlin600.sms.aliyun.core.util;

import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.http.MethodType;
import xyz.rexlin600.sms.aliyun.core.constant.SmsConst;
import xyz.rexlin600.sms.aliyun.core.request.SmsRequest;

/**
 * The type Sms request util.
 *
 * @author hekunlin
 * @since 2020 /9/2
 */
public class SmsRequestUtil {

	/**
	 * Build common request common request.
	 *
	 * @param request  the request
	 * @param domain   the domain
	 * @param regionId the region id
	 * @return the common request
	 */
	public static CommonRequest buildCommonRequest(SmsRequest request, String domain, String regionId) {
		CommonRequest commonRequest = new CommonRequest();
		commonRequest.setSysMethod(MethodType.POST);
		commonRequest.setSysAction(SmsConst.SEND_SMS);
		commonRequest.setSysDomain(domain);
		commonRequest.setSysVersion(SmsConst.VERSION);
		commonRequest.putQueryParameter(SmsConst.REGION_PARAM, regionId);
		commonRequest.putQueryParameter("PhoneNumbers", request.getPhone());
		commonRequest.putQueryParameter("SignName", request.getSignName());
		commonRequest.putQueryParameter("TemplateCode", request.getTemplateCode());
		commonRequest.putQueryParameter("TemplateParam", JSONUtil.toJsonStr(request.getTemplateParam()));
		return commonRequest;
	}

}