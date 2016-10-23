package servlet;

import entity.Score;
import entity.User;
import org.codehaus.jackson.map.ObjectMapper;
import util.shen.SqlOperation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by shenxingbo  on 2016/10/13.
 * function: 返回折线图展示数据的jason形式
 */
public class GetScore1 extends HttpServlet {
    private Connection conn;  //数据库连接对象
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Score> list;
    private int u_id;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //首先获得用户的id
        u_id = 1;
        try {
            SqlOperation sqlOperation = new SqlOperation();
            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
            conn  = sqlOperation.initSqlOperation();
            String sql = "SELECT r_time, SUM(score) sumscore FROM record  WHERE u_id = ? GROUP BY r_num";    //改动
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,u_id);
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<Score>();
            while (resultSet.next()){
                String r_time = resultSet.getString("r_time");
                String sumScore = resultSet.getString("sumscore");
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(new Long(r_time));
                Date date = c.getTime();
                r_time = sdf.format(date);
                Score score = new Score(r_time,sumScore);
                list.add(score);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(list);
        //System.out.println(result);
        request.setAttribute("result",result);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(result);
        request.getRequestDispatcher("/shen/ShowChart.jsp").forward(request,response);
    }
}
