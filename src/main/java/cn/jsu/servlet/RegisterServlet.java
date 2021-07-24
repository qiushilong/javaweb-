package cn.jsu.servlet;

import cn.jsu.bean.User;
import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
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
            //判断账号是否存在
            if(dao.isExistAccountId(accountId)){
                request.setAttribute("accountid_error","账号已经存在!");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }else{
                User user=new User(null,null,null,accountId,password,null,null,null);
                dao.add(user);
                request.setAttribute("success_tip","注册成功！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else{
            //验证码错误
            request.setAttribute("cc_error","验证码错误!");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
