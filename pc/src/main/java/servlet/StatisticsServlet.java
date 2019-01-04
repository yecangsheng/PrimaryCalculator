package servlet;

import entity.PersonAvgScor;
import entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by yufujia on 2016/10/22.
 */
public class StatisticsServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet set;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到下拉框参数
        String rankData = "";
        String refreshData = "";
        List<Integer> userList = new ArrayList<Integer>();
        List<PersonAvgScor> pvsAll  = new ArrayList<PersonAvgScor>();
        String sql = "";

        String rank = (String) request.getParameter("rank");
        String refresh = (String) request.getParameter("refresh");
        //从session中获得id
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int u_id = user.getId();
        ServletContext context = this.getServletContext();

        //用户查询所有用户的id;
        try{
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pc","root","root");
            //首先查询所有的用户的id
            sql = "select id from user";
            preparedStatement = conn.prepareStatement(sql);
            set = preparedStatement.executeQuery();
            while(set.next()){
                int u_id_all = set.getInt("id");
                userList.add(u_id_all);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        if (rank != null){
            try{
                //就每次for循环来说：用于查询指定u_id，r_rank 的所有轮次的总成绩，目标：想要获得是所有用户在该级别的平均分
                sql = "select count(*) number,sum(score)sum_score from  (select sum(score) score from record where u_id = ? and r_rank = ? group by  r_num) As new_record ";

                for (int i=0; i < userList.size(); i++){
                    PersonAvgScor pvs = new PersonAvgScor();
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1,userList.get(i));
                    //System.out.println("ddddddddddddddddddd   "+ rank);
                    preparedStatement.setString(2,rank);
                    set = preparedStatement.executeQuery();
                    set.next();
                    String number = set.getString("number");
                    String sum_score = set.getString("sum_score");
                    if (sum_score == null){
                        continue;
                    }
                    int avgScore = avgScore = Integer.parseInt(sum_score) / Integer.parseInt(number);

                    pvs.setAvgScore(avgScore);
                    pvs.setU_id(userList.get(i));
                    pvs.setRank(Integer.parseInt(rank));

                    pvsAll.add(pvs);
                }
                //System.out.println("shebn  " + pvsAll.get(0).toString()+ "  " + pvsAll.get(1).toString()+"  " + pvsAll.get(2).toString()+"  " + pvsAll.get(3).toString());
                pvsAll.add(new PersonAvgScor(5,200.0,4));
                pvsAll.add(new PersonAvgScor(6,200.0,3));
            }catch(Exception e){
                e.printStackTrace();
            }

            //下面使用对成绩进行排序
            int[] number = {0,0,0,0,0};
            Collections.sort(pvsAll);
            for ( int i=0; i<pvsAll.size(); i++){
                double avgscore = pvsAll.get(i).getAvgScore();
                if (avgscore <= 60){
                    ++number[0];
                }else if(avgscore <= 70){
                    ++number[1];
                }else if(avgscore <= 80){
                    ++number[2];
                }else if(avgscore <= 90){
                    ++number[3];
                }else if(avgscore <= 100){
                    ++number[4];
                }
            }
            for (int i=0; i<5; i++){
                rankData = rankData+number[i]+" ";
            }
            context.setAttribute("pie",rankData);
        }

        //对于refresh的回应
        if(refresh!=null){
            try{
                //就每次for循环来说：用于查询指定u_id，r_rank 的所有轮次的总成绩，目标：想要获得是所有用户在该级别的平均分
                sql = "select count(*) number,sum(score)sum_score from  (select sum(score) score from record where u_id = ? and r_rank = ? group by  r_num) As new_record ";
                for (int j=1; j<=4; j++){ //代表的是4个等级
                    for (int i=0; i < userList.size(); i++){
                        PersonAvgScor pvs = new PersonAvgScor();
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setInt(1,userList.get(i));
                        preparedStatement.setString(2,j+""); //指的是某个等级
                        set = preparedStatement.executeQuery();
                        set.next();
                        String number = set.getString("number");
                        String sum_score = set.getString("sum_score");
                        if (sum_score == null){
                            continue;
                        }
                        int avgScore = Integer.parseInt(sum_score) / Integer.parseInt(number);
                        pvs.setAvgScore(avgScore);
                        pvs.setU_id(userList.get(i));
                        pvs.setRank(Integer.parseInt(rank));
                        pvsAll.add(pvs);

                    }
                }
                pvsAll.add(new PersonAvgScor(5,1000.0,4));
                pvsAll.add(new PersonAvgScor(6,200.0,3));
                pvsAll.add(new PersonAvgScor(7,400.0,3));
                Map<Integer,Double> map = new HashMap<Integer, Double>() ;
                for (int i=0; i<pvsAll.size(); i++){
                    int key = pvsAll.get(i).getU_id();
                    double value = pvsAll.get(i).getAvgScore();
                    int rankMap = pvsAll.get(i).getRank();
                    if (map.containsKey(key)){
                        switch (rankMap){
                            case 1:
                                map.put(key,map.get(key) + value*0.1);
                                break;
                            case 2:
                                map.put(key,map.get(key) + value*0.2);
                                break;
                            case 3:
                                map.put(key,map.get(key) + value*0.3);
                                break;
                            case 4:
                                map.put(key,map.get(key) + value*0.4);
                                break;
                        }
                    }else {
                        map.put(key,value);
                    }
                }
                double[] allScore = new double[map.size()];
                int i = 0,j = 0;
                for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                    allScore[i++] = entry.getValue();
                }
                Arrays.sort(allScore);
                if(allScore.length > 0) {
                    for (j = allScore.length - 1; allScore[j] != map.get(u_id); j--) ;
                    System.out.println(j + "   " + map.size() + "  " + pvsAll.size() + "  " + map.get(u_id));
                    refreshData = (allScore.length - j) * 1.0 / map.size() + "";
                } else {
                    refreshData  = "1.0";
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            context.setAttribute("rank",refreshData);
        }




        request.getRequestDispatcher("Statistics/status.jsp").forward(request,response);
        //System.out.println(a);
        //response.getWriter().print("aaaaa");
        //System.out.println(rank);

    }

}
