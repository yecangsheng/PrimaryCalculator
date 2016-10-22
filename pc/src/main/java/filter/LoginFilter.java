package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Bruce-Jiang on 2016/10/9.
 * 过滤器配置类
 */
public class LoginFilter implements Filter {
    private FilterConfig config = null;
    public void destroy() {
        config = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //把ServletRequest和ServletResponse转换成真正的类型
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response  = (HttpServletResponse)resp;
        HttpSession session = request.getSession();

        String loginStrings = config.getInitParameter("loginStrings");
        String includeStrings = config.getInitParameter("includeStrings");
        String redirectPath = request.getContextPath() + config.getInitParameter("redirectPath");

        String[] loginList = loginStrings.split(";");
        String[] includeList = includeStrings.split(";");

        //对指定后缀进行过滤
        if(!this.isContains(request.getRequestURI(),includeList)){
            chain.doFilter(req,resp);
            return ;
        }
        if (this.isContains(request.getRequestURI(), loginList)) {// 对登录页面不进行过滤
            chain.doFilter(request, response);
            return;
        }

        //是否登陆
        if(null == session.getAttribute("user")){
            response.sendRedirect(redirectPath);
            return ;
        }else{
            chain.doFilter(req,resp);
            return;
        }


    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        System.out.println("init Filter");
    }

    private  static boolean isContains(String container, String[] regx) {
        boolean result = false;

        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }
}
