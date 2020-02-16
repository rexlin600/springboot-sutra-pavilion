package xyz.rexlin600.aop.aspect.one;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志切面
 * <p>
 * 使用 @Aspect 定义一个切面
 *
 * @author: rexlin600
 * @date: 2020-02-16
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Aspect
@Component
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
        log.info("====================before拦截开启====================");
        String url = this.request.getRequestURI();
        String requestMethod = this.request.getMethod();
        Signature signature = joinPoint.getSignature();
        String clazzMethod = signature.getDeclaringTypeName() + "." + signature.getName();
        String params = JSONObject.toJSONString(this.request.getParameterMap());
        String uuid = IdUtil.simpleUUID();
        REQUEST_UUID.set(uuid);
        REQUEST_START_TIME.set(System.currentTimeMillis());
        log.info("=====================uuid:{},请求路径:{}========================", uuid, url);
        log.info("=====================uuid:{},请求方法:{}========================", uuid, requestMethod);
        log.info("=====================uuid:{},请求类全路径:{}========================", uuid, clazzMethod);
        log.info("=====================uuid:{},请求参数:{}========================", uuid, params);
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
        log.info("====================around拦截开启====================");
        log.info("====================around前置做某些事====================");
        Object result = joinPoint.proceed();
        log.info("====================around后置做某些事====================");
        return result;
    }

    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "appLogPointCut()")
    public void doAfterReturning(Object ret) {
        log.info("====================afterReturning拦截开启====================");
        //String result = JSONObject.toJSONString(ret);
        String uuid = REQUEST_UUID.get();
        long costTime = System.currentTimeMillis() - REQUEST_START_TIME.get();
        log.info("=====================uuid:{},耗时:{}ms,返回结果:{}========================", uuid, costTime, ret.toString());
    }


}
