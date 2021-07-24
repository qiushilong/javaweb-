package cn.jsu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.jsp"}, filterName = "AuthorityFilter")
public class AuthorityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
//        System.out.println(requestURI);

        //如果不是放行的jsp
        if (!(requestURI.equals("/webchat/") || requestURI.contains("top") || requestURI.contains("bottom") || requestURI.contains("index") || requestURI.contains("login") || requestURI.contains("register"))) {
            //判断是否登陆了
            if (request.getSession().getAttribute("USER") == null) {
                //没登陆，跳到登陆界面
                request.setAttribute("accountid_error","请先登陆");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            } else {
                //登陆了，放行
                chain.doFilter(req, resp);
            }
        } else {
            //非限制页面，放行
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
