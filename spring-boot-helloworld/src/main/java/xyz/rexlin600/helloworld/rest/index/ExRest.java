package xyz.rexlin600.helloworld.rest.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.rexlin600.helloworld.exception.BaseException;

/**
 * 全局异常测试接口
 *
 * @author hekunlin
 */
@Controller
public class ExRest {

	// 这里不能使用 @RestController

	/**
	 * 默认Index
	 *
	 * @param map map
	 * @return the string
	 */
	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("url", "http://www.rexlin600.com");
		return "index";
	}

	/**
	 * 默认异常返回
	 *
	 * @return the string
	 * @throws Exception exception
	 */
	@RequestMapping("/default")
	public String defaultEx() throws Exception {
		throw new RuntimeException("默认全局异常");
	}

	/**
	 * 自定义异常返回
	 *
	 * @return the string
	 * @throws BaseException base exception
	 */
	@RequestMapping("/base")
	public String myEx() throws BaseException {
		throw new BaseException("自定义异常");
	}


}