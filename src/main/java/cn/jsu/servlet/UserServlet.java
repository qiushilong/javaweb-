package cn.jsu.servlet;

import cn.jsu.bean.User;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/userServlet")
@MultipartConfig
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        UserDao dao=new UserDao();
        HttpSession session = request.getSession();

        //图片上传
        Part part = request.getPart("headShot");
        //上传文件名字
        String cd = part.getHeader("Content-Disposition");
        //截取不同类型的文件需要自行判断
        String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
        //创建保存文件目录
        String dir=this.getServletContext().getRealPath("/img/user_headShot");
        //判断目录是否存在
        File imgDir=new File(dir);
        if(!imgDir.exists()){
            imgDir.mkdirs();
        }
        //上传到服务器文件的路径
        if(!fileName.equals(""))
            part.write(dir+'/'+fileName);

        String userName =request.getParameter("userName");
        String gender =request.getParameter("gender");
        String qq =request.getParameter("qq");
        String attitude =request.getParameter("attitude");

        User temp=(User)session.getAttribute("USER");
        if(fileName.equals(""))
            fileName=temp.getHeadShot();
        User user=new User(temp.getId(),userName,gender,temp.getAccountId(),temp.getPassword(),qq,attitude,fileName);
        session.setAttribute("USER",user);

        dao.update(user);

        response.sendRedirect("/webchat/chat.jsp");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
