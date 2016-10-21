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
 * Created by Bruce-Jiang on 2016/10/21.
 */
public class QueryForRecordHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RecordService rs = new RecordServiceImp();
        int u_id = ((User)session.getAttribute("user")).getId();
        List<ExpRec> list = rs.queryForManyRecords(u_id);
        session.setAttribute("resultList",list);
        response.sendRedirect("page/showStuReHis.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
