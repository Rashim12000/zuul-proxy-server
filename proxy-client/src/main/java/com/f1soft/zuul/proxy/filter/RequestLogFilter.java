package com.f1soft.zuul.proxy.filter;


import com.f1soft.zuul.proxy.manager.RequestResponseManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
public class RequestLogFilter extends ZuulFilter {

    @Autowired
    private RequestResponseManager requestResponseManager;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("Authorization", "Basic banksmart-omni-credential");

        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        requestResponseManager.log("redirecting to " + request.getRequestURL().toString());
        return null;
    }
}
