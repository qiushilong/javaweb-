package cn.jsu.dao;

import cn.jsu.bean.Room;
import cn.jsu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from room";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Room room) {
        String sql = "insert into room values(null,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, room.getManagerId());
            ps.setString(2, room.getRoomAccountId());
            ps.setString(3, room.getRoomName());
            ps.setString(4, room.getMsg());
            ps.setBoolean(5, room.isPrivate());
            ps.setString(6, room.getRoomDeclaration());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Room room) {
        String sql = "update room set room_id= ?, manager_id=?, room_account_id=?, room_name=?, msg=?, is_private=?, room_declaration=? where room_id = " + room.getId();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, room.getId());
            ps.setInt(2, room.getManagerId());
            ps.setString(3, room.getRoomAccountId());
            ps.setString(4, room.getRoomName());
            ps.setString(5, room.getMsg());
            ps.setBoolean(6, room.isPrivate());
            ps.setString(7, room.getRoomDeclaration());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from room where room_id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room get(int id) {
        Room room = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from room where room_id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                room = new Room();
                room.setId(rs.getInt(1));
                room.setManagerId(rs.getInt(2));
                room.setRoomAccountId(rs.getString(3));
                room.setRoomName(rs.getString(4));
                room.setMsg(rs.getString(5));
                room.setPrivate(rs.getBoolean(6));
                room.setRoomDeclaration(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    //根据room_user表的id，获取其对应的所有room
    public ArrayList<Room> getRoomListByIdFormRoomUser(int userId) {
        ArrayList<Room> roomList=new ArrayList<>();
        Room room = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select room.room_id,manager_id,room_account_id,room_name,msg,is_private,room_declaration from room,room_user where room.room_id=room_user.room_id and room_user.user_id = " + userId;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                room = new Room();
                room.setId(rs.getInt(1));
                room.setManagerId(rs.getInt(2));
                room.setRoomAccountId(rs.getString(3));
                room.setRoomName(rs.getString(4));
                room.setMsg(rs.getString(5));
                room.setPrivate(rs.getBoolean(6));
                room.setRoomDeclaration(rs.getString(7));
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public Room getByRoomAccountId(String roomAccountId) {
        Room room = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from room where room_account_id = '" + roomAccountId+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                room = new Room();
                room.setId(rs.getInt(1));
                room.setManagerId(rs.getInt(2));
                room.setRoomAccountId(rs.getString(3));
                room.setRoomName(rs.getString(4));
                room.setMsg(rs.getString(5));
                room.setPrivate(rs.getBoolean(6));
                room.setRoomDeclaration(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public boolean isExistRoomAccountId(String roomAccountId) {
        boolean flag=false;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from room where room_account_id ='" + roomAccountId+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                flag= true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int getIdByRoomAccountId(String roomAccountId) {
        int id = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from room where room_account_id ='" + roomAccountId+"'";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                id=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getRoomAccountIdById(int id) {
        String roomAccountId = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select room_account_id from room where room_id =" + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                roomAccountId=rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomAccountId;
    }
}
