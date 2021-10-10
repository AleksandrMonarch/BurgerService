package com.example.burgerservice.aspect;

import com.example.burgerservice.constant.LogProperties;
import com.example.burgerservice.rest.client.AuditServiceClient;
import com.example.burgerservice.rest.dto.BaseResponseDto;
import com.example.burgerservice.rest.dto.DataRequestDto;
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
    private final AuditServiceClient auditServiceClient;

    @Autowired
    public LogAspect(LogProperties logProperties, AuditServiceClient auditServiceClient) {
        this.logProperties = logProperties;
        this.auditServiceClient = auditServiceClient;
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
        logMessage(stringBuilder.toString());
        return returnValue;
    }

    private void logMessage(String message) {
        log.info(message);
        sendData(message);
    }

    private void sendData(String methodData) {
        BaseResponseDto baseResponseDto = auditServiceClient.sendMethodLogData(new DataRequestDto<>(methodData));
    }

    private void setProceedingMethodSignature(StringBuilder stringBuilder, ProceedingJoinPoint proceedingJoinPoint) {
        var methodSignature = proceedingJoinPoint.getSignature();
        stringBuilder.append(String.format("Method: %s%n",
                logProperties.getIsLogMethodDetailSignature() ?
                        methodSignature.toLongString() : methodSignature.toShortString()));
    }
}
