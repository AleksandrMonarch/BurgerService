package com.example.burgerservice.aspect;

import com.example.burgerservice.constant.LogProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@ConditionalOnProperty(prefix = "log", name = "enabled", havingValue = "true")
public class LogAspect {

    private final LogProperties logProperties;

    @Autowired
    public LogAspect(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Pointcut("@annotation(com.example.burgerservice.annotation.Log)")
    public void log() {
    }

    @Around("log()")
    public Object logAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnValue = null;
        log.debug("LogAdvice() method started");
        StringBuilder stringBuilder = new StringBuilder();
        setProceedingMethodSignature(stringBuilder, proceedingJoinPoint);
        if (logProperties.getIsLogMethodExecutionTime()) {
            long start = System.currentTimeMillis();
            try {
                returnValue = proceedingJoinPoint.proceed();
                long during = System.currentTimeMillis() - start;
                stringBuilder.append(String.format("Method execution time: %d%n", during));
            } catch (Throwable e) {
                log.error("", e);
                throw e;
            }
        } else {
            try {
                returnValue = proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                log.error("", e);
                throw e;
            }
        }
        stringBuilder.append(String.format("Method %s successfully ended",
                proceedingJoinPoint.getSignature().getName()));
        return returnValue;
    }

    private void logMessage(String message) {
        log.info(message);
//        sendMessageToREmoteService()
    }

    private void setProceedingMethodSignature(StringBuilder stringBuilder, ProceedingJoinPoint proceedingJoinPoint) {
        var methodSignature = proceedingJoinPoint.getSignature();
        stringBuilder.append(String.format("Method: %s%n",
                logProperties.getIsLogMethodDetailSignature() ?
                        methodSignature.toLongString() : methodSignature.toShortString()));
    }
}
