package xyz.rexlin600.validation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.rexlin600.validation.common.apiparam.Response;
import xyz.rexlin600.validation.common.apiparam.ResponseGenerator;
import xyz.rexlin600.validation.common.statuscode.StatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * GlobalException 类
 *
 * @author: hekunlin
 * @date: 2020/1/10
 */
@Slf4j
@ControllerAdvice(basePackages = {"xyz.rexlin600"})
public class GlobalException {

    /**
     * Servlet请求绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response handleServletRequestBindingException(ServletRequestBindingException e) {
        log.error("param bind failure", e);
        return ResponseGenerator.fail(StatusCode.PARAM_ERROR.getCode(), e.getMessage());
    }

    /**
     * 参数验证失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response handleValidationException(ConstraintViolationException e) {
        for (ConstraintViolation<?> s : e.getConstraintViolations()) {
            log.error("Request parameter is invalid {}", s.getMessage());
            return ResponseGenerator.fail(StatusCode.PARAM_ERROR.getCode(), s.getMessage());
        }
        return ResponseGenerator.fail(StatusCode.PARAM_ERROR);
    }

    /**
     * 绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public Response<Void> handle(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = this.buildErrorMessage(bindingResult);
        log.warn(errorMessage);
        FieldError fieldError = e.getFieldError();
        String message = fieldError.getDefaultMessage();
        return ResponseGenerator.fail(StatusCode.PARAM_ERROR.getCode(), message, errorMessage);
    }

    /**
     * 方法参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Response<Void> handle(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = this.buildErrorMessage(bindingResult);
        log.warn(errorMessage);
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String message = "";
        if (null != fieldErrors && fieldErrors.size() > 0) {
            for (int i = 0; i < fieldErrors.size(); i++) {
                message = message + " " + Optional.ofNullable(fieldErrors.get(i)).map(m -> m.getDefaultMessage()).orElse("");
            }
        }

        return ResponseGenerator.fail(StatusCode.PARAM_ERROR.getCode(), message, errorMessage);
    }

    // -----------------------------------------------------------------------------------------------
    // EXTRA METHOD
    // -----------------------------------------------------------------------------------------------

    private String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder("BindException. ");
        sb.append("Field error in object '").append(bindingResult.getObjectName()).append("'. [").append(bindingResult.getTarget()).append("]");
        bindingResult.getFieldErrors().forEach((error) -> {
            sb.append("\r\n on field '").append(error.getField()).append("': ");
            sb.append("rejected value [").append(error.getRejectedValue()).append("]. ");
            sb.append("default message [").append(error.getDefaultMessage()).append("]");
        });
        return sb.toString();
    }


}