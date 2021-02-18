package cn.tugos.logaop.demo.aspectj;

import cn.hutool.json.JSONUtil;
import cn.tugos.logaop.demo.utils.HttpUtils;
import cn.tugos.logaop.demo.vo.LogVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author qinming
 * @date 2021-02-18 10:48:47
 * <p> 使用aop记录controller请求日志 </p>
 */
@Slf4j
@Aspect
@Component
public class LogAspectj {

    /**
     * 切入点
     */
    @Pointcut("execution(public * cn.tugos.logaop.demo.controller.*Controller.*(..))")
    public void logAspect() {

    }

    /**
     * 环绕操作
     * 记录请求日志
     */
    @Around("logAspect()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 记录执行时间
        long startTime = System.currentTimeMillis();
        //处理请求
        Object result = joinPoint.proceed();

        //记录日志
        try {
            LogVO logVo = LogVO.builder()
                    //请求ip
                    .ip(HttpUtils.getIpAddress(request))
                    //请求路径
                    .url(request.getRequestURL().toString())
                    //处理的类
                    .classMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()))
                    //处理的方法类型
                    .httpMethod(request.getMethod())
                    //请求参数
                    .requestParams(getRequestParam(joinPoint))
                    //返回结果
                    .result(result)
                    //耗时
                    .timeCost(System.currentTimeMillis() - startTime).build();
            log.info("请求参数和处理信息 : {}", JSONUtil.toJsonStr(logVo));
        } catch (Exception e) {
            log.warn("记录请求日志失败：", e);
        }
        return result;
    }

    /**
     * 获取请求参数
     */
    private Object[] getRequestParam(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                //ServletRequest，ServletResponse,MultipartFile不能序列化
                continue;
            }
            arguments[i] = args[i];
        }
        return arguments;
    }


}
