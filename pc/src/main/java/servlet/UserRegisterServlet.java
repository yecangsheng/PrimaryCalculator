package servlet;

import entity.User;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 */
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        //这里可能抛出例外
        int identity = Integer.parseInt(request.getParameter("identity"));
        UserService us =new UserServiceImp();
        int re = us.register(new User(email,nickname,password,identity));
        HttpSession session = request.getSession();
        if(re <= 0){
            session.setAttribute("message","已经存在该用户请直接登陆");
        }else{
            session.setAttribute("message","注册成功，请登录");
        }
        request.getRequestDispatcher("page/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
