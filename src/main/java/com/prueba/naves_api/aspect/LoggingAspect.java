package com.prueba.naves_api.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* getNaveById*(..))", throwing = "ex")
    public void logNegativeError(Exception ex) {
            if(ex.getMessage().contains("negativo")){
                logger.error(ex.getMessage());
            }
    }
}