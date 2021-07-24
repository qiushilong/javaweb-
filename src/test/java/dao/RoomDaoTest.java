package dao;

import cn.jsu.bean.Room;
import cn.jsu.dao.RoomDao;
import org.junit.jupiter.api.Test;

public class RoomDaoTest {
    RoomDao dao=new RoomDao();;
    @Test
    public void testAdd(){
        //测试添加
        Room room =new Room(1,1,"123456deroom","超级聊天室","系统通知的房间",false,"欢迎加入!");
        dao.add(room);
    }
    @Test
    public void testGetTotal(){
        //测试统计
        System.out.println(dao.getTotal());
    }
    @Test
    public void testUpdate(){
        //测试修改
        Room room =new Room(1,1,"123456deroom22","超级聊天室","系统通知的房间",false,"欢迎加入!");
        dao.update(room);
    }
    @Test
    public void testDelete(){
        //测试删除
        dao.delete(1);
    }
    @Test
    public void testGet(){
        //测试获取
        System.out.println(dao.get(1));
    }
}
