package com.shanshuan.apigetewayzuul.fillter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.ZuulFilterInitializer;

import javax.servlet.http.HttpServletRequest;

public class AccessFillter extends ZuulFilter {
private static Logger logger= LoggerFactory.getLogger(AccessFillter.class);
    @Override
    public String filterType() {
        return "pre";
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
        HttpServletRequest request = ctx.getRequest();
        logger.info("------------send {}requst to {}",request.getMethod(),request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");
        if(accessToken==null){
            logger.warn("accessToken tokeen is empty");
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            return null;
        }
        return null;
    }
}
