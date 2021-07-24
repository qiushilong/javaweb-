package dao;

import cn.jsu.bean.ChatMsg;
import cn.jsu.dao.ChatMsgDao;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ChatMsgDaoTest {
    ChatMsgDao dao=new ChatMsgDao();;
    @Test
    public void testAdd(){
        //测试添加
        ChatMsg chatMsg=new ChatMsg(1,1,2,new Date(),"大家欢迎加入哦");
        dao.add(chatMsg);
    }
    @Test
    public void testGetTotal(){
        //测试统计
        System.out.println(dao.getTotal());
    }
    @Test
    public void testUpdate(){
        //测试修改
        ChatMsg chatMsg=new ChatMsg(1,1,1,new Date(),"大家欢迎加入哦!!!");
        dao.update(chatMsg);
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
