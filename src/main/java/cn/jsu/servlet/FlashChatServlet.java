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

@WebServlet("/flashChatServlet")
public class FlashChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();

        //获取session里的数据，就是chat.jsp的界面
        ArrayList<ChatMsg> chatMsgList2 = (ArrayList<ChatMsg>) session.getAttribute("chatMsgList");
        ArrayList<User> userList = (ArrayList<User>) session.getAttribute("userList");
        int roomId = (int) session.getAttribute("roomId");
        String roomAccountId = (String) session.getAttribute("roomAccountId");

        //从数据库查找
        ChatMsgDao dao = new ChatMsgDao();
        UserDao dao2=new UserDao();
        ArrayList<ChatMsg> chatMsgList=null;
        //得到最新的数据
        chatMsgList = dao.getByRoomAccountId(roomAccountId);
        for(ChatMsg temp:chatMsgList){
            //得到所有信息的发送人信息集合
            int userId = temp.getUserId();
            User user=dao2.get(userId);
            userList.add(user);
        }


        //没有最新数据
        if(chatMsgList.size()==chatMsgList2.size())
            return;

        //有最新的数据，把最新的数据返回
        for(int i=chatMsgList2.size();i<chatMsgList.size();i++){
            User user=dao2.get(chatMsgList.get(i).getUserId());
            //让接收者用*/*分割
            writer.write(user.getId()+"*/*");
            writer.write(user.getName()+"*/*");
            writer.write(user.getHeadShot()+"*/*");
            writer.write(chatMsgList.get(i).getDate().toString()+"*/*");
            writer.write(chatMsgList.get(i).getContent()+"*/*");
        }

        session.setAttribute("chatMsgList", chatMsgList);
        session.setAttribute("userList", userList);
        session.setAttribute("roomId",roomId);
        session.setAttribute("roomAccountId",roomAccountId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
