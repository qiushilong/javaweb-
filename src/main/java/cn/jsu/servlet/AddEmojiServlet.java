package cn.jsu.servlet;

import cn.jsu.bean.Emoji;
import cn.jsu.bean.User;
import cn.jsu.dao.EmojiDao;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addEmojiServlet")
@MultipartConfig
public class AddEmojiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        EmojiDao dao=new EmojiDao();
        HttpSession session = request.getSession();

        //图片上传
        Part part = request.getPart("addEmoji");
        //上传文件名字
        String cd = part.getHeader("Content-Disposition");
        //截取不同类型的文件需要自行判断
        String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
        //创建保存文件目录
        String dir=this.getServletContext().getRealPath("/img/user_emoji");
        //判断目录是否存在
        File imgDir=new File(dir);
        if(!imgDir.exists()){
            imgDir.mkdirs();
        }
        //上传到服务器文件的路径
        if(!fileName.equals(""))
            part.write(dir+'/'+fileName);

        User user= (User) session.getAttribute("USER");
        Emoji emoji=new Emoji(null,user.getId(),fileName,fileName);

        dao.add(emoji);

        //在session里加上新加的表情
        ArrayList<Emoji> emojilist = (ArrayList<Emoji>) session.getAttribute("EMOJILIST");
        emojilist.add(emoji);

        session.setAttribute("EMOJILIST",emojilist);

        response.sendRedirect("/webchat/chat.jsp?roomAccountId="+session.getAttribute("roomAccountId"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
