package org.data.nowgnas.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class InterfaceObserverAspect {

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 1000;

    @Around("@annotation(interfaceObserver)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, InterfaceObserver interfaceObserver) throws Throwable {
        int attempt = 0;
        while (true) {
            try {
                long startTime = System.currentTimeMillis();
                Object result = joinPoint.proceed();
                long endTime = System.currentTimeMillis();
                log.info("Method {} executed in {} ms", joinPoint.getSignature(), endTime - startTime);
                return result;
            } catch (Exception e) {
                attempt++;
                log.warn("Attempt {} of {} failed for method {} with error: {}", attempt, MAX_RETRIES, joinPoint.getSignature(), e.getMessage());
                if (attempt >= MAX_RETRIES) {
                    log.error("Method {} failed after {} attempts. Parameters: {}", joinPoint.getSignature(), MAX_RETRIES, Arrays.toString(joinPoint.getArgs()));
                    throw e;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ie);
                }
            }
        }
    }
}
