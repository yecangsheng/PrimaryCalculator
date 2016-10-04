package servlet;

import entity.User;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Bruce-Jiang on 2016/10/2.
 */
public class UserLoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uMail = request.getParameter("uMail");
        String uPassword = request.getParameter("uPassword");
        int uIdentity = Integer.parseInt(request.getParameter("uIdentity"));
        UserService us = new UserServiceImp();
        User user= us.login(new User(uMail,uPassword,uIdentity));
        if(null == user){
            request.setAttribute("message","用户不存在或者是密码错误");
            request.getRequestDispatcher("page/login.jsp").forward(request,response);
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            request.getRequestDispatcher("page/index.jsp").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
