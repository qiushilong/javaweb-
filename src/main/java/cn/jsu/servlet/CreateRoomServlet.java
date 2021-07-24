package cn.jsu.servlet;

import cn.jsu.bean.Room;
import cn.jsu.bean.RoomUser;
import cn.jsu.bean.User;
import cn.jsu.dao.RoomDao;
import cn.jsu.dao.RoomUserDao;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@WebServlet("/createRoomServlet")
public class CreateRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        RoomDao dao=new RoomDao();
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();

        String roomAccountId =request.getParameter("roomAccountId");
        String roomName =request.getParameter("roomName");
        String roomDeclaration =request.getParameter("roomDeclaration");
        String msg =request.getParameter("msg");
        String isPrivate =request.getParameter("isPrivate");

        if(!dao.isExistRoomAccountId(roomAccountId)){
            //房间账号不存在
            User temp=(User) session.getAttribute("USER");
            int managerId=temp.getId();
            Room room=new Room(null,managerId,roomAccountId,roomName,msg,isPrivate.equals("是")?true:false,roomDeclaration);
            dao.add(room);
            int roomId = dao.getIdByRoomAccountId(roomAccountId);
            //把自己加入房间
            RoomUserDao dao2=new RoomUserDao();
            RoomUser roomUser = new RoomUser(null,roomId,managerId,new Date());
            dao2.add(roomUser);

            HashMap<Integer, Integer> roomonlinenum = (HashMap<Integer, Integer>)servletContext.getAttribute("ROOMONLINENUM");
            if (roomonlinenum.get(roomId) == null){
                roomonlinenum.put(roomId, 1);
            }
            else{
                roomonlinenum.replace(roomId, roomonlinenum.get(roomId) + 1);
            }

            servletContext.setAttribute("ROOMONLINENUM",roomonlinenum);

            request.setAttribute("createRoomTip","创建成功!");
            request.getRequestDispatcher("/chat.jsp").forward(request,response);
        }else{
            //房间账号存在
            request.setAttribute("createRoomTip","房间号已存在!");
            request.getRequestDispatcher("/chat.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
