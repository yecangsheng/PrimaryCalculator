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
        RecordService rs = new RecordServiceImp();
        //获取用户ID
        User user = (User)session.getAttribute("user");
        Integer u_id = user.getId();

        //获取Session中的num 用户做题次数
        Integer num  = (Integer)session.getAttribute("num");
        //如果没有存储该值，是首次登陆
        if(num == null || num == 0){
            num = rs.queryForFrequency(u_id);
            //如果取值仍旧是0,将num置为1
            if(num == 0){
                num = 1;
            }
            //将其放入session域中
            session.setAttribute("num",num);
        }

        Integer rank = (Integer)session.getAttribute("rank");
        //获取运算表达式ID
        Integer e_id = Integer.parseInt(request.getParameter("e_id"));
        //获取用户给出的答案
        String result = request.getParameter("result");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        System.out.println(date);
        Record record = new Record(u_id,e_id,result,date,num,rank);

        //写入数据
        rs.insertOneRecord(record);
        //取一条四则运算表达式
        ExpressionService es = new ExpressionServiceImp();
        Expression expression =  (Expression)session.getAttribute("exp");
        if(expression != null){
            session.setAttribute("exp",null);
        }
        //获取下一题

        expression = es.obtainOneExp(rank);
        session.setAttribute("exp",expression);
        System.out.println("OK, that's all");
        response.sendRedirect("page/showExpression.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
