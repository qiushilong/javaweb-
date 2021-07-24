package dao;

import cn.jsu.bean.User;
import cn.jsu.dao.UserDao;
import org.junit.jupiter.api.Test;

public class UserDaoTest {
    UserDao dao=new UserDao();;
    @Test
    public void testAdd(){
        //测试添加
        User user =new User(1,"小花","女","123456","123456","123456","开心",null);
        dao.add(user);
    }
    @Test
    public void testGetTotal(){
        //测试统计
        System.out.println(dao.getTotal());
    }
    @Test
    public void testUpdate(){
        //测试修改
        User user =new User(2,"小蓝","男","1234567","1234567","123456","开心",null);
        dao.update(user);
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
    @Test
    public void testExistAccountId(){
        //测试获取
        System.out.println(dao.isExistAccountId("123456"));
    }
}
