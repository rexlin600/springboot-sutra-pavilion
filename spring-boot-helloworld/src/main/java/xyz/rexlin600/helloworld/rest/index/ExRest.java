package xyz.rexlin600.helloworld.rest.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.rexlin600.helloworld.exception.BaseException;

/**
 * 这里不能使用 @RestController
 */

/**
 * ExRest
 *
 * @author: hekunlin
 * @since: 2020/1/10
 */
@Controller
public class ExRest {

    /**
     * 1. 额外-返回index
     *
     * @param map map
     * @return 字符串
     */
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("url", "http://www.rexlin600.com");
        return "index";
    }

    /**
     * 2. 额外-默认全局异常
     *
     * @return 字符串
     * @throws Exception 异常
     */
    @RequestMapping("/default")
    public String defaultEx() throws Exception {
        throw new RuntimeException("默认全局异常");
    }

    /**
     * 3. 额外-自定义异常
     *
     * @return 字符串
     * @throws BaseException 基础异常
     */
    @RequestMapping("/base")
    public String myEx() throws BaseException {
        throw new BaseException("自定义异常");
    }


}