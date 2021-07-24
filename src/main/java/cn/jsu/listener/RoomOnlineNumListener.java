package cn.jsu.listener;

import cn.jsu.bean.User;
import cn.jsu.dao.RoomUserDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebListener()
public class RoomOnlineNumListener implements HttpSessionAttributeListener {

    //第一个参数存房间id，第二个参数存房间人数
    Map<Integer, Integer> roomOnlineNum = null;

    // Public constructor is required by servlet spec
    public RoomOnlineNumListener() {
    }


    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */

        String name = sbe.getName();
        if (name.equals("USER")) {

            if (sbe.getSession().getServletContext().getAttribute("ROOMONLINENUM") != null)
                roomOnlineNum = (HashMap<Integer, Integer>) sbe.getSession().getServletContext().getAttribute("ROOMONLINENUM");
             else
                roomOnlineNum = new HashMap<>();

            User user = (User) sbe.getValue();


            ArrayList<Integer> roomIds = new RoomUserDao().getRoomIdsbyUserId(user.getId());


            for(int i=0;i<roomIds.size();i++){
                if(roomOnlineNum.get(roomIds.get(i))==null){
                    roomOnlineNum.put(roomIds.get(i),1);
                }else{
                    roomOnlineNum.replace(roomIds.get(i),roomOnlineNum.get(roomIds.get(i))+1);
                }
            }

            sbe.getSession().getServletContext().setAttribute("ROOMONLINENUM", roomOnlineNum);
        }
    }
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
        String name = sbe.getName();
        if(name.equals("USER")){
            if (sbe.getSession().getServletContext().getAttribute("ROOMONLINENUM") != null)
                roomOnlineNum = (HashMap<Integer, Integer>) sbe.getSession().getServletContext().getAttribute("ROOMONLINENUM");

            User user = (User) sbe.getValue();

            ArrayList<Integer> roomIds = new RoomUserDao().getRoomIdsbyUserId(user.getId());


            for (int i = 0; i < roomIds.size(); i++) {
                if (roomOnlineNum.get(roomIds.get(i)) == null) {
                    roomOnlineNum.put(roomIds.get(i), 1);
                } else {
                    roomOnlineNum.replace(roomIds.get(i), roomOnlineNum.get(roomIds.get(i)) - 1);
                    if(roomOnlineNum.get(roomIds.get(i))==0)
                        roomOnlineNum.remove(roomIds.get(i));
                }
            }


            sbe.getSession().getServletContext().setAttribute("ROOMONLINENUM", roomOnlineNum);

        }



    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
