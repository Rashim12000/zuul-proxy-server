package com.f1soft.zuul.proxy.filter;


import com.f1soft.zuul.proxy.manager.RequestResponseManager;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Rashim Dhaubanjar
 */
@Slf4j
@Component
public class ResponseLogFilter extends ZuulFilter {

    @Autowired
    private RequestResponseManager requestResponseManager;

    @Override
    public String filterType() {
        return POST_TYPE;
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

        RequestContext context = RequestContext.getCurrentContext();
        try (final InputStream responseDataStream = context.getResponseDataStream()) {

            if (responseDataStream == null) {
                log.info("BODY: {}", "");
                return null;
            }

            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            log.info("BODY: {}", responseData);

            requestResponseManager.log(responseData);
            context.setResponseBody(responseData);
        } catch (Exception e) {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

        return null;
    }
}
