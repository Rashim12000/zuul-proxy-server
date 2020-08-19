package com.f1soft.zuul.proxy.manager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
public class RequestResponseManager {

    public void log(String body) {
        log.info("Log response using manager : {}", body);
    }
}
