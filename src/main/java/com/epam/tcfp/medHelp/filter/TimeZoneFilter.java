package com.epam.tcfp.medHelp.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.TimeZone;

public class TimeZoneFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
