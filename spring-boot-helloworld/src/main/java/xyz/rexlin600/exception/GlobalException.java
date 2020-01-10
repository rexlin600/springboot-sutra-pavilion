package xyz.rexlin600.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.rexlin600.entity.dto.ExDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalException 类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@ControllerAdvice(basePackages = {"xyz.rexlin600"}) // basePackages 可用于指定哪些目录发生异常需要被处理
public class GlobalException {

    /**
     * 默认的异常处理器，返回错误页面
     *
     * @param request
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExHander(HttpServletRequest request, Exception ex) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    /**
     * 自定义异常处理器，直接返回 ExDTO，注意需要加上 @ResponseBody
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ExDTO<String> jsonErrorHandler(HttpServletRequest req, BaseException e) throws Exception {
        ExDTO<String> r = new ExDTO<>();
        r.setMsg(e.getMessage());
        r.setCode(1005);
        r.setData("Hello,I'm your BaseException");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}