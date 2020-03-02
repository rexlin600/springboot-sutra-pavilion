package xyz.rexlin600.aop.aspect.classic;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志切面
 * <p>
 * 使用 @Aspect 定义一个切面
 * <p>
 * 注意：如需使用这种方式，请在 {@link SysLogAspect} 前增加 @Component 注解
 *
 * @author hekunlin
 * @date: 2020-02-16
 */
@SuppressWarnings("ALL")
@Slf4j
@Aspect
public class SysLogAspect {

    @Autowired
    private HttpServletRequest request;

    private static final ThreadLocal<String> REQUEST_UUID = new ThreadLocal<>();
    private static final ThreadLocal<Long> REQUEST_START_TIME = new ThreadLocal<>();

    /**
     * 切入点
     */
    @Pointcut("execution( * xyz.rexlin600.aop.controller.*.*(..))")
    public void appLogPointCut() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("appLogPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        String url = this.request.getRequestURI();
        String requestMethod = this.request.getMethod();
        Signature signature = joinPoint.getSignature();
        String clazzMethod = signature.getDeclaringTypeName() + "." + signature.getName();
        String params = JSONObject.toJSONString(this.request.getParameterMap());
        String uuid = IdUtil.simpleUUID();
        REQUEST_UUID.set(uuid);
        REQUEST_START_TIME.set(System.currentTimeMillis());
        log.info("==>       经典方式-前置通知：uuid:{},请求路径:{}，请求方法:{}，请求类全路径:{}，请求参数:{}", uuid, url, requestMethod, clazzMethod, params);
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("appLogPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        log.info("==>       经典方式-后置通知：around后置做某些事");
        return result;
    }

    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "appLogPointCut()")
    public void doAfterReturning(Object ret) {
        String uuid = REQUEST_UUID.get();
        long costTime = System.currentTimeMillis() - REQUEST_START_TIME.get();
        log.info("==>       经典方式-环绕通知：uuid:{},耗时:{}ms,返回结果:{}", uuid, costTime, ret.toString());
        REQUEST_UUID.remove();
        REQUEST_START_TIME.remove();
    }


}
