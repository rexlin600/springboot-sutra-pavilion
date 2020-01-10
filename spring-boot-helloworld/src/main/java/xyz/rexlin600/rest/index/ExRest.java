package xyz.rexlin600.rest.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.exception.BaseException;

/**
 * ExRest rest请求
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@Controller // 这里不能使用 @RestController
public class ExRest {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("url", "http://www.rexlin600.com");
        return "index";
    }

    @RequestMapping("/default")
    public String defaultEx() throws Exception {
        throw new Exception("默认全局异常");
    }

    @RequestMapping("/base")
    public String myEx() throws BaseException {
        throw new BaseException("自定义异常");
    }


}