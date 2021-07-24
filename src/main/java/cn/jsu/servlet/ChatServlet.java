package cn.jsu.servlet;

import cn.jsu.bean.ChatMsg;
import cn.jsu.bean.User;
import cn.jsu.dao.ChatMsgDao;
import cn.jsu.dao.RoomDao;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/chatServlet")
public class ChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ChatMsgDao dao = new ChatMsgDao();
        UserDao dao2=new UserDao();
        RoomDao dao3=new RoomDao();
        ArrayList<ChatMsg> chatMsgList=null;
        ArrayList<User> userList =new ArrayList<>();
        HttpSession session=request.getSession();

        if (request.getParameterMap().size() != 0) {
            //有请求参数
            String roomAccountId = request.getParameter("roomAccountId");
            //得到该房间所有信息的集合
            chatMsgList = dao.getByRoomAccountId(roomAccountId);
            for(ChatMsg temp:chatMsgList){
                //得到所有信息的发送人信息集合
                int userId = temp.getUserId();
                User user=dao2.get(userId);
                userList.add(user);
            }
            //根据roomAccountId的到roomId
            int roomId=dao3.getIdByRoomAccountId(roomAccountId);

            session.setAttribute("chatMsgList", chatMsgList);
            session.setAttribute("userList", userList);
            session.setAttribute("roomId",roomId);
            session.setAttribute("roomAccountId",roomAccountId);

            response.sendRedirect("/webchat/chat.jsp?roomAccountId="+roomAccountId);
//            System.out.println(request.getSession().getAttribute("USER")+"     "+chatMsgList+"    "+roomId+"    "+roomAccountId+"\n\n");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
