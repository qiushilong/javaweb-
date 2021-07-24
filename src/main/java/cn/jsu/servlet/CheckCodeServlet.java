package cn.jsu.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        //1）创建一张图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2）美化图片
        Graphics g = image.getGraphics();
        //填充
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        //边框
        g.setColor(Color.gray);
        g.drawRect(0, 0, width - 1, height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz1234567890";
        //随机字符
        Random ran = new Random();
        StringBuilder sb=new StringBuilder();
        g.setFont(new Font("宋体",Font.BOLD,20));
        g.setColor(Color.black);
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            sb.append(ch);
            g.drawString(ch + "", width / 5 * i, height / 2+5);
        }
        String checkCode_session = sb.toString();
        //将验证码存入session
        request.getSession().setAttribute("checkCode_session",checkCode_session);
        //干扰线
        g.setColor(Color.green);
        for (int i = 0; i < 5; i++) {
            int x1 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int x2 = ran.nextInt(width);
            int y2 = ran.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
        //3）输出图片
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

}
