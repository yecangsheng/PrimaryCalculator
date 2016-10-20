package servlet;

import entity.ExpRec;
import entity.User;
import service.RecordService;
import service.RecordServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Bruce-Jiang on 2016/10/17.
 */
public class ResocrdQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer num = (Integer)session.getAttribute("num");
        RecordService rs = new RecordServiceImp();
        int u_id = ((User)session.getAttribute("user")).getId();
        System.out.println(num);
        //如果没有传值则取用户最近一次的作为分析
        if(num == null || num == 0){
            num = rs.queryForFrequency(u_id);
            System.out.println(num);
        }
        List<ExpRec> list = rs.queryForManyRecords(num,u_id);
        session.setAttribute("resultList",list);
        response.sendRedirect("page/expressionResult.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
