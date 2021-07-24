package dao;

import cn.jsu.bean.RoomUser;
import cn.jsu.dao.RoomUserDao;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class RoomUserDaoTest {
    RoomUserDao dao=new RoomUserDao();;
    @Test
    public void testAdd(){
        //测试添加
        RoomUser roomUser=new RoomUser(1,1,1,new Date());
        dao.add(roomUser);
    }
    @Test
    public void testGetTotal(){
        //测试统计
        System.out.println(dao.getTotal());
    }
    @Test
    public void testUpdate(){
        //测试修改
        RoomUser roomUser=new RoomUser(1,1,1,new Date());
        dao.update(roomUser);
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

