package cn.jsu.dao;
 
import java.sql.Connection;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.jsu.bean.User;
import cn.jsu.util.DBUtil;

public class UserDao {

    /**
     * 得到user表的数据个数
     * @return
     */
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from user";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
 
    public void add(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getAccountId());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getQq());
            ps.setString(6, user.getAttitude());
            ps.setString(7, user.getHeadShot());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void update(User user) {
        String sql = "update user set user_name= ?, gender=?, user_account_id=?, user_password=?, qq=?, attitude =?, headshot=? where user_id = "+user.getId();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getAccountId());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getQq());
            ps.setString(6, user.getAttitude());
            ps.setString(7, user.getHeadShot());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from user where user_id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public User get(int id) {
        User user = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from user where user_id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setGender(rs.getString(3));
                user.setAccountId(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setQq(rs.getString(6));
                user.setAttitude(rs.getString(7));
                user.setHeadShot(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isExistAccountId(String accountId) {
        boolean flag=false;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from user where user_account_id ='" + accountId+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                flag= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public User getByAccountIdPwd(String accountId,String password) {
        User user = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from user where user_account_id = '" + accountId+"' and user_password= '"+password+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setGender(rs.getString(3));
                user.setAccountId(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setQq(rs.getString(6));
                user.setAttitude(rs.getString(7));
                user.setHeadShot(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}