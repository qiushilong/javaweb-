package cn.jsu.servlet;

import cn.jsu.bean.ChatMsg;
import cn.jsu.dao.ChatMsgDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/sendMsgServlet")
public class SendMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ChatMsgDao dao=new ChatMsgDao();

        String content = request.getParameter("content");
        String userId_str = request.getParameter("userId");
        String roomId_str = request.getParameter("roomId");
        String roomAccountId = request.getParameter("roomAccountId");

        int userId=Integer.parseInt(userId_str);
        int roomId=Integer.parseInt(roomId_str);

        ChatMsg chatMsg = new ChatMsg(null, roomId, userId, new Date(), content);
        dao.add(chatMsg);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
