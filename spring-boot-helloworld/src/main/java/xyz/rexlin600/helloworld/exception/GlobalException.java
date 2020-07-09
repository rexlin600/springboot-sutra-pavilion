package xyz.rexlin600.helloworld.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.rexlin600.helloworld.entity.dto.ExDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalException 类
 *
 * @author: hekunlin
 * @since: 2020/1/10
 */
@ControllerAdvice(basePackages = {"xyz.rexlin600"})
public class GlobalException {

    /**
     * 默认的异常处理器，返回错误页面
     *
     * @param request 请求
     * @param ex      异常
     * @return ModelAndView
     * @throws Exception 异常
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
     * @param req 请求
     * @param e   异常
     * @return ExDTO
     * @throws Exception 异常
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