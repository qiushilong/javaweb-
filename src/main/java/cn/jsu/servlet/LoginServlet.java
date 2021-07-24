package cn.jsu.servlet;

import cn.jsu.bean.Emoji;
import cn.jsu.bean.User;
import cn.jsu.dao.EmojiDao;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        UserDao dao = new UserDao();

        String accountId = request.getParameter("accountId");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //验证码验证
        HttpSession session=request.getSession();
        String checkCode_session=(String)session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");


        //判断验证码是否正确
        if(checkCode_session.equalsIgnoreCase(checkCode)){
            //验证码正确
            //判断账号密码是否正确
            User user=dao.getByAccountIdPwd(accountId,password);
            if(user!=null){
                //账号密码正确
                session.setAttribute("USER",user);

                //获取用户的表情包保存到session
                EmojiDao dao2=new EmojiDao();
                ArrayList<Emoji> emojiList = dao2.getEmojiList(user.getId());
                session.setAttribute("EMOJILIST",emojiList);

                //判断是否填写过基本信息
                if(user.getGender()==null){
                    //没填过
                    response.sendRedirect("/webchat/user.jsp");
                }else{
                    //填过
                    response.sendRedirect("/webchat/chat.jsp");
                }
            }else{
                //账号密码不正确
                request.setAttribute("accountid_error","账号或密码错误!");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else{
            //验证码错误
            request.setAttribute("cc_error","验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
