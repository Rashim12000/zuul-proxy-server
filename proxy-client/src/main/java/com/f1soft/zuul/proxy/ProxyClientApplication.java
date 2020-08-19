package com.f1soft.zuul.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Rashim Dhaubanjar
 */
@EnableZuulProxy
@SpringBootApplication
public class ProxyClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ProxyClientApplication.class, args);
    }
}
