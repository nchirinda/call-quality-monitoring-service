package com.geneinsure.cqmservice.config;

import com.geneinsure.cqmservice.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Component
public class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Initializing Logging Filter: {}", Formatter.toJson(this));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info("> CQM Service Request > - {}: {}, parameters={}", req.getMethod(), req.getRequestURI(), Formatter.toJson(req.getParameterMap()));

        chain.doFilter(request, response);

        log.info("< CQM Service Response < - {}: {}", res.getStatus(), res.getHeaderNames().toArray());
    }

    @Override
    public void destroy() {
        log.warn("Destructing Logging Filter: {}", this);
    }
}
