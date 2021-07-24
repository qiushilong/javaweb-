package cn.jsu.dao;

import cn.jsu.bean.Emoji;
import cn.jsu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class EmojiDao {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from emoji";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Emoji emoji) {
        String sql = "insert into emoji values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, emoji.getUserId());
            ps.setString(2, emoji.getEmojiName());
            ps.setString(3, emoji.getPath());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Emoji emoji) {
        String sql = "update emoji set emoji_id= ?, user_id=?, emoji_name=?, path=? where emoji_id = " + emoji.getId();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, emoji.getId());
            ps.setInt(2, emoji.getUserId());
            ps.setString(3, emoji.getEmojiName());
            ps.setString(4, emoji.getPath());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from emoji where emoji_id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Emoji get(int id) {
        Emoji emoji = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from emoji where emoji_id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                emoji = new Emoji();
                emoji.setId(rs.getInt(1));
                emoji.setUserId(rs.getInt(2));
                emoji.setEmojiName(rs.getString(3));
                emoji.setPath(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emoji;
    }

    public ArrayList<Emoji> getEmojiList(int userId){
        ArrayList<Emoji> arrayList=new ArrayList<>();
        Emoji emoji = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from emoji where user_id = " + userId;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                emoji = new Emoji();
                emoji.setId(rs.getInt(1));
                emoji.setUserId(rs.getInt(2));
                emoji.setEmojiName(rs.getString(3));
                emoji.setPath(rs.getString(4));
                arrayList.add(emoji);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
