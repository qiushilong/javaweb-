package cn.jsu.servlet;

import cn.jsu.dao.RoomDao;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/examineRoomIdServlet")
public class ExamineRoomIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        RoomDao dao=new RoomDao();
        PrintWriter printWriter=response.getWriter();
        String roomAccountId = request.getParameter("roomAccountId");
        boolean existRoomId = dao.isExistRoomAccountId(roomAccountId);
        if(existRoomId)
            printWriter.write("房间账号已存在!");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
