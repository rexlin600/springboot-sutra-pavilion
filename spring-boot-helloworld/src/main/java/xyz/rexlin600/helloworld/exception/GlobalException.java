package xyz.rexlin600.helloworld.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.rexlin600.helloworld.entity.dto.ExDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Global exception
 *
 * @author hekunlin
 */
@ControllerAdvice(basePackages = {"xyz.rexlin600"})
public class GlobalException {

	/**
	 * Default ex hander model and view
	 *
	 * @param request request
	 * @param ex      ex
	 * @return the model and view
	 * @throws Exception exception
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
	 * Json error handler ex dto
	 *
	 * @param req req
	 * @param e   e
	 * @return the ex dto
	 * @throws Exception exception
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