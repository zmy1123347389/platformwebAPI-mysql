package com.behere.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Behere
 */
@WebFilter(filterName="CorsFilter",urlPatterns="/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)resp;
        //*号设置任意端口都可访问，也可指定端口如http://localhost:8182
        response.setHeader("Access-Control-Allow-Origin", "*");
        //设置"POST, GET, OPTIONS, DELETE"请求才可以访问
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        chain.doFilter(req, resp);
    }
    @Override
    public void destroy() {
    }

}
