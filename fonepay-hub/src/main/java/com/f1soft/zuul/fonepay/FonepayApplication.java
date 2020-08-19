package com.f1soft.zuul.fonepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Rashim Dhaubanjar
 */
@EnableZuulProxy
@SpringBootApplication
public class FonepayApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FonepayApplication.class, args);
    }
}
