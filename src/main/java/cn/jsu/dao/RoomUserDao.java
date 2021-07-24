package cn.jsu.dao;

import cn.jsu.bean.Room;
import cn.jsu.bean.RoomUser;
import cn.jsu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class RoomUserDao {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from room_user";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(RoomUser roomUser) {
        String sql = "insert into room_user values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, roomUser.getRoomId());
            ps.setInt(2, roomUser.getUserId());
            ps.setTimestamp(3, new Timestamp(roomUser.getSpeakTime().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(RoomUser roomUser) {
        String sql = "update room_user set contrast_id= ?, room_id=?, user_id=?, speak_time=? where contrast_id = " + roomUser.getContrastId();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, roomUser.getContrastId());
            ps.setInt(2, roomUser.getRoomId());
            ps.setInt(3, roomUser.getUserId());
            ps.setTimestamp(4, new Timestamp(roomUser.getSpeakTime().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from room_user where contrast_id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RoomUser get(int id) {
        RoomUser roomUser = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from room_user where contrast_id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                roomUser = new RoomUser();
                roomUser.setContrastId(rs.getInt(1));
                roomUser.setRoomId(rs.getInt(2));
                roomUser.setUserId(rs.getInt(3));
                roomUser.setSpeakTime(rs.getTimestamp(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomUser;
    }

    public RoomUser getByRoomIdAndUserId(int roomId,int userId) {
        RoomUser roomUser = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from room_user where room_id = " + roomId+" and user_id="+userId;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                roomUser = new RoomUser();
                roomUser.setContrastId(rs.getInt(1));
                roomUser.setRoomId(rs.getInt(2));
                roomUser.setUserId(rs.getInt(3));
                roomUser.setSpeakTime(rs.getTimestamp(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomUser;
    }

    public ArrayList<Integer> getRoomIdsbyUserId(int userId){
        ArrayList<Integer> temp = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select room_id from room_user where  user_id="+userId;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Integer room_id = rs.getInt(1);
                temp.add(room_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
