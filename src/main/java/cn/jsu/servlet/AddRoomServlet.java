package cn.jsu.servlet;

import cn.jsu.bean.RoomUser;
import cn.jsu.bean.User;
import cn.jsu.dao.RoomDao;
import cn.jsu.dao.RoomUserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

@WebServlet("/addRoomServlet")
public class AddRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ServletContext servletContext = request.getSession().getServletContext();
        PrintWriter printWriter = response.getWriter();
        RoomUserDao dao = new RoomUserDao();

        String roomIdStr = request.getParameter("roomId");
        Integer roomId = Integer.parseInt(roomIdStr);
        User temp = (User) request.getSession().getAttribute("USER");
        int userId = temp.getId();

        RoomUser roomUser = dao.getByRoomIdAndUserId(roomId, userId);

        if (roomUser == null) {
            //没有加入该聊天室
            RoomUser roomUser2 = new RoomUser(null, roomId, userId, new Date());
            dao.add(roomUser2);

            HashMap<Integer, Integer> roomonlinenum = (HashMap<Integer, Integer>) servletContext.getAttribute("ROOMONLINENUM");
            if (roomonlinenum.get(roomId) == null){
                roomonlinenum.put(roomId, 1);
            }
            else{
                roomonlinenum.replace(roomId, roomonlinenum.get(roomId) + 1);
            }
            servletContext.setAttribute("ROOMONLINENUM",roomonlinenum);
            printWriter.write("你加入了该聊天室!刷新后可见！");
            //异步提交，无法请求转发，重定向
        } else {
            printWriter.write("你已经加入了该聊天室!");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
