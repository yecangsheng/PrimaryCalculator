package servlet;

import entity.Expression;
import entity.Record;
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
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bruce-Jiang on 2016/10/9.
 */
public class RecordWriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获取用户ID
        User user = (User)session.getAttribute("user");
        Integer u_id = user.getId();
        //获取运算表达式ID
        Integer e_id = Integer.parseInt(request.getParameter("e_id"));
        //获取用户给出的答案
        String result = request.getParameter("result");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        System.out.println(date);
        Record record = new Record(u_id,e_id,result,date);
        RecordService rs = new RecordServiceImp();
        //写入数据
        rs.insertOneRecord(record);
        //取一条四则运算表达式
        ExpressionService es = new ExpressionServiceImp();
        Expression expression =  (Expression)session.getAttribute("exp");
        if(expression != null){
            session.setAttribute("exp",null);
        }
        expression = es.obtainOneExp(0);
        session.setAttribute("exp",expression);
        System.out.println("OK, that's all");
        response.sendRedirect("page/showExpression.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
