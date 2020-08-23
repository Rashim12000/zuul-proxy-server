package com.f1soft.zuul.proxy.filter;


import com.f1soft.zuul.proxy.manager.RequestResponseManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

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

        try {
            InputStream in = (InputStream) ctx.get("requestEntity");
            if (in == null) {
                in = ctx.getRequest().getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));

            log.info("Request form client : {}", body);
            body = body.replace("Sheldon", "f1soft");

            log.info("Request form client modified by proxy : {}", body);

            ctx.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));

        } catch (Exception e) {
            log.error("Error : ", e);
        }
        //add body
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        requestResponseManager.log("redirecting to " + request.getRequestURL().toString());
        return null;
    }
}
