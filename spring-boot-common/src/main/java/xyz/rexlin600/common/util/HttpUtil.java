package xyz.rexlin600.common.util;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Http util
 *
 * @author hekunlin
 * @since 2020-08-07
 */
public class HttpUtil {

	/**
	 * DEFAULT_CHARSET
	 */
	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * PHCCM
	 */
	private static final PoolingHttpClientConnectionManager PHCCM =
			new PoolingHttpClientConnectionManager();
	/**
	 * CONFIG
	 */
	private static final RequestConfig CONFIG =
			RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(10000).build();
	/**
	 * client
	 */
	private static CloseableHttpClient client =
			HttpClients.custom().setConnectionManager(PHCCM).build();

	static { // 这边来设置并发
		PHCCM.setDefaultMaxPerRoute(10);
		PHCCM.setMaxTotal(20);
	}

	/**
	 * Do get string
	 *
	 * @param uri uri
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String doGet(String uri) throws IOException {
		HttpGet get = new HttpGet(uri);
		get.setConfig(CONFIG);
		CloseableHttpResponse httpResponse = client.execute(get);
		return generateHttpResponse(httpResponse);
	}

	/**
	 * Do get string
	 *
	 * @param url        url
	 * @param queryParam query param
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String doGet(String url, Map<String, String> queryParam) throws IOException {
		URI uri = generateUrlParams(url, queryParam);

		HttpGet get = new HttpGet(uri);
		get.setConfig(CONFIG);
		CloseableHttpResponse httpResponse = client.execute(get);
		return generateHttpResponse(httpResponse);
	}

	/**
	 * POST请求, json主体，可用于raw请求
	 *
	 * @param url    api
	 * @param params query string
	 * @param json   请求体内是json字符串
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String doPost(String url, Map<String, String> params, String json)
			throws IOException {
		URI uri = generateUrlParams(url, params);
		HttpPost post = new HttpPost(uri);
		post.setConfig(CONFIG);
		HttpEntity entity = new StringEntity(json, DEFAULT_CHARSET);
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/json");
		CloseableHttpResponse httpResponse = client.execute(post);
		return generateHttpResponse(httpResponse);
	}

	/**
	 * POST请求, json主体
	 *
	 * @param url     api
	 * @param params  query string
	 * @param json    请求体内是json字符串
	 * @param headers 请求header
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String doPost(
			String url, Map<String, String> params, String json, Map<String, String> headers)
			throws IOException {
		URI uri = generateUrlParams(url, params);
		HttpPost post = new HttpPost(uri);
		post.setConfig(CONFIG);
		headers.forEach((key, value) -> post.addHeader(key, value));
		HttpEntity entity = new StringEntity(json, DEFAULT_CHARSET);
		post.setEntity(entity);
		post.setHeader("Content-Type", "application/json");
		CloseableHttpResponse httpResponse = client.execute(post);
		return generateHttpResponse(httpResponse);
	}

	/**
	 * POST请求,form主体
	 *
	 * @param url  api
	 * @param form form表单
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String doPost(String url, Map<String, String> form) throws IOException {
		URI uri = generateUrlParams(url, null);
		List<NameValuePair> urlEncodedForm = generateUrlEncodeForm(form);
		HttpPost post = new HttpPost(uri);
		post.setConfig(CONFIG);
		HttpEntity entity = new UrlEncodedFormEntity(urlEncodedForm, DEFAULT_CHARSET);
		post.setEntity(entity);
		CloseableHttpResponse httpResponse = client.execute(post);
		return generateHttpResponse(httpResponse);
	}

	/**
	 * Do post string
	 *
	 * @param url url
	 * @return the string
	 * @throws IOException io exception
	 */
	public static String doPost(String url) throws IOException {
		URI uri = generateUrlParams(url, null);
		HttpPost post = new HttpPost(uri);
		post.setConfig(CONFIG);
		CloseableHttpResponse httpResponse = client.execute(post);
		return generateHttpResponse(httpResponse);
	}

	/**
	 * Generate url params uri
	 *
	 * @param url    url
	 * @param params params
	 * @return the uri
	 */
	private static URI generateUrlParams(String url, Map<String, String> params) {
		URI uri = null;
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (null != params) {
				for (Entry<String, String> entry : params.entrySet()) {
					uriBuilder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			uri = uriBuilder.build();
		} catch (URISyntaxException e) {
			return uri;
		}
		return uri;
	}

	/**
	 * Generate url encode form list
	 *
	 * @param form form
	 * @return the list
	 */
	private static List<NameValuePair> generateUrlEncodeForm(Map<String, String> form) {
		if (form == null || form.size() == 0) {
			return null;
		}
		List<NameValuePair> list = new ArrayList<>();
		for (Entry<String, String> entry : form.entrySet()) {
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	/**
	 * Generate http response string
	 *
	 * @param httpResponse http response
	 * @return the string
	 * @throws IOException io exception
	 */
	private static String generateHttpResponse(CloseableHttpResponse httpResponse)
			throws IOException {
		if (httpResponse == null) {
			return null;
		}
		HttpEntity entity = null;
		entity = httpResponse.getEntity();
		String res = EntityUtils.toString(entity, DEFAULT_CHARSET);
		httpResponse.close();
		return res;
	}
}
