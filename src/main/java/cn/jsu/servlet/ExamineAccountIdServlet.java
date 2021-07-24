package cn.jsu.servlet;

import cn.jsu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/examineAccountIdServlet")
public class ExamineAccountIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        UserDao dao=new UserDao();
        PrintWriter printWriter=response.getWriter();

        String accountId = request.getParameter("accountId");;

        boolean existAccountId = dao.isExistAccountId(accountId);
        if(existAccountId)
            printWriter.write("账号已存在!");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
