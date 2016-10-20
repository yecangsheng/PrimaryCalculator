package servlet;

import entity.Expression;
import entity.User;
import service.ExpressionService;
import service.ExpressionServiceImp;
import service.RecordService;
import service.RecordServiceImp;

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
        HttpSession session = request.getSession();
        //获取参数
        String rankStr = request.getParameter("rank");
        int rank;
        if(rankStr != null){ // 如果是第一次选择
            rank = Integer.parseInt(rankStr);
            //向session存储相应值
            session.setAttribute("rank",rank);
        }else{//存储在Session中的难度等级
            rank = (Integer) session.getAttribute("rank");
        }

        RecordService rs = new RecordServiceImp();
        User user = (User)session.getAttribute("user");
        //第几次答题
        Integer num = (Integer)session.getAttribute("num");
        if(num==null || num ==0){
            num = rs.queryForFrequency(user.getId());
        }
        session.setAttribute("num",num+1);
        System.out.println("OK");
        ExpressionService es = new ExpressionServiceImp();
        Expression exp = es.obtainOneExp(rank);
        session.setAttribute("exp",exp);
        Integer time = 20*60;
        session.setAttribute("leftTime",time);
        session.setAttribute("expNum",1);
        response.sendRedirect("page/showExpression.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
