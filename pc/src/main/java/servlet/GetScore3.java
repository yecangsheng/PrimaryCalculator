package servlet;

import entity.Score;
import org.codehaus.jackson.map.ObjectMapper;
import util.shen.SqlOperation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shen on 2016/10/20.
 */
public class GetScore3 extends HttpServlet {
    private Connection conn;  //数据库连接对象
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private List<Score> list;
    private String username;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = 0;
        String rank2  = (String)request.getParameter("rank3");
        String period2 = (String)request.getParameter("period3");
        String begintime2 = (String)request.getParameter("begintime3");
        String endtime2 = (String)request.getParameter("endtime3");
        //System.out.println(rank2 + " "+ period2 + " "+ begintime2 + " "+ endtime2);
        try {
            //下面将日期转换成毫秒
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Long begintime = sdf.parse(begintime2).getTime();
            Long endtime = sdf.parse(endtime2).getTime();
            Long period =  new Long(period2) * 24 * 3600*1000;
            int u_id = 1;
            SqlOperation sqlOperation = new SqlOperation();
            conn  = sqlOperation.initSqlOperation();
            long time = begintime + period;
            list = new ArrayList<Score>();

            while(time < endtime){
          //      System.out.println(flag +"  "+ time + " "+ endtime);
                flag++;
                String sql = "SELECT SUM(score) score FROM record WHERE  r_time >= ? AND r_time <= ? AND r_rank = ? AND u_id = ? GROUP BY r_num";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1,begintime);
                preparedStatement.setLong(2,time);
                preparedStatement.setString(3,rank2);
                preparedStatement.setInt(4,u_id);
                //System.out.println(preparedStatement);
                resultSet = preparedStatement.executeQuery();
                int count = 0;
                int sum = 0;
                List<Integer> sumCollection = new ArrayList<Integer>();
                while (resultSet.next()){
                    count++;
                    sumCollection.add(new Integer(resultSet.getInt("score")));
                    sum = resultSet.getInt("score")+sum;
                }
                begintime = time;
                time = begintime + period;
                if(count != 0){
                    int avgSum = sum / count;
                    System.out.println(sum + "  " + count +"  "+ avgSum);
                    long result = 0;
                    for (int i=0; i< sumCollection.size(); i++){
                        System.out.println(sumCollection.get(i) + "  " + avgSum);
                        result = (sumCollection.get(i) - avgSum) *(sumCollection.get(i) - avgSum) + result;
                    }
                    System.out.println(result);
                    result = result / count;
                    Score score = new Score((flag * new Integer(period2))+"",result+"");
                    list.add(score);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(list);
        //System.out.println(result);
        // request.setAttribute("result",result);
        response.getWriter().print(result);
      //  System.out.println("bbbbb");
    }
}
