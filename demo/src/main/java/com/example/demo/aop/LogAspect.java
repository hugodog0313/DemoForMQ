package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

  @Autowired Tracer tracer;

  @Pointcut("execution(* com.example.demo.service.UserServiceImpl..*(..))")
  public void pointcut() {}

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
    //    Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getName());
    String traceId = showTraceId();
    Object[] args = joinPoint.getArgs();
    String name = joinPoint.getSignature().getName();
    log.info(String.valueOf(args) + name+traceId);
    Object result = joinPoint.proceed();
    return result;
  }

  public String showTraceId() {
    String result = "";
    String traceId = tracer.currentSpan().context().traceId();
    String spanId = tracer.currentSpan().context().spanId();
    result = String.format("[ traceId: %s, spanId: %s ]", traceId, spanId);
    return result;
  }
}
