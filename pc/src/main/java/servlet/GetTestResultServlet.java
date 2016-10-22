package servlet;

import com.google.gson.Gson;
import entity.User;
import jdk.nashorn.internal.parser.JSONParser;
import service.RecordService;
import service.RecordServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruce-Jiang on 2016/10/21.
 */
public class GetTestResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer num = (Integer)session.getAttribute("num");
        Integer u_id = (Integer)(((User)session.getAttribute("user")).getId());
        RecordService rs = new RecordServiceImp();
        if(num == null || num == 0){
            num = rs.queryForFrequency(u_id);
        }
        long[] result = rs.queryForResult(num,u_id);

        //session.setAttribute("result",result);
        //request.setAttribute("result",result);
        System.out.println("执行了"+result[0]);
        session.setAttribute("leftTime",0);
       // response.sendRedirect("page/showExpression.jsp");
       // request.getRequestDispatcher("page/showExpression.jsp").forward(request,response);
        List<Long> list = new ArrayList<Long>();
        list.add(result[0]);
        list.add(result[1]);
        list.add(result[0]*5);
        String json = new Gson().toJson(list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
       // response.getWriter().write(0);
    }
}
