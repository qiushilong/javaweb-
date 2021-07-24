package cn.jsu.servlet;

import cn.jsu.bean.Room;
import cn.jsu.dao.RoomDao;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findRoomServlet")
public class FindRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        RoomDao dao=new RoomDao();

        String roomAccountId = request.getParameter("roomAccountId");

        boolean existRoomAccountId = dao.isExistRoomAccountId(roomAccountId);

        if(existRoomAccountId){
            //房间号存在，查看房间信息
            Room room=dao.getByRoomAccountId(roomAccountId);
            String managerName=new UserDao().get(room.getManagerId()).getName();
            request.setAttribute("room",room);
            request.setAttribute("managerName",managerName);
            request.getRequestDispatcher("/addRoom.jsp").forward(request,response);
        }else{
            //房间号不存在，返回错误信息
            request.setAttribute("error","房间号不存在!");
            request.getRequestDispatcher("/findRoom.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
