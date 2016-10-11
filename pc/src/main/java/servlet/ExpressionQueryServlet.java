package servlet;

import entity.Expression;
import entity.User;
import service.ExpressionService;
import service.ExpressionServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Bruce-Jiang on 2016/10/8.
 */
public class ExpressionQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String rankStr = request.getParameter("rank");
        int rank = 0;
        if(rankStr != null){
            rank = Integer.parseInt(rankStr);
        }
        System.out.println("OK");
        ExpressionService es = new ExpressionServiceImp();
        Expression exp = es.obtainOneExp(rank);
        HttpSession session = request.getSession();
        session.setAttribute("exp",exp);
        response.sendRedirect("page/showExpression.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
