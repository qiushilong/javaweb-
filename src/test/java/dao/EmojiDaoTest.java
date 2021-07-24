package dao;

import cn.jsu.bean.Emoji;
import cn.jsu.dao.EmojiDao;
import org.junit.jupiter.api.Test;

public class EmojiDaoTest {
    EmojiDao dao=new EmojiDao();;
    @Test
    public void testAdd(){
        //测试添加
        Emoji emoji=new Emoji(null,1,"表情1","f://");
        dao.add(emoji);
    }
    @Test
    public void testGetTotal(){
        //测试统计
        System.out.println(dao.getTotal());
    }
    @Test
    public void testUpdate(){
        //测试修改
        Emoji emoji=new Emoji(1,1,"表情2","f://");
        dao.update(emoji);
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
