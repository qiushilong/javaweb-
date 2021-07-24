package cn.jsu.dao;

import cn.jsu.bean.ChatMsg;
import cn.jsu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class ChatMsgDao {

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from chat_msg";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(ChatMsg chatMsg) {
        String sql = "insert into chat_msg values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, chatMsg.getRoomId());
            ps.setInt(2, chatMsg.getUserId());
            ps.setTimestamp(3,  new Timestamp(chatMsg.getDate().getTime()));
            ps.setString(4, chatMsg.getContent());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ChatMsg chatMsg) {
        String sql = "update chat_msg set room_msg_id= ?, room_id=?, user_id=?, date=?, content=? where room_msg_id = " + chatMsg.getRoomMsgId();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, chatMsg.getRoomMsgId());
            ps.setInt(2, chatMsg.getRoomId());
            ps.setInt(3, chatMsg.getUserId());
            ps.setTimestamp(4,  new Timestamp(chatMsg.getDate().getTime()));
            ps.setString(5, chatMsg.getContent());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from chat_msg where room_msg_id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChatMsg get(int id) {
        ChatMsg chatMsg = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from chat_msg where room_msg_id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                chatMsg = new ChatMsg();
                chatMsg.setRoomMsgId(rs.getInt(1));
                chatMsg.setRoomId(rs.getInt(2));
                chatMsg.setUserId(rs.getInt(3));
                chatMsg.setDate(rs.getTimestamp(4));
                chatMsg.setContent(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatMsg;
    }

    public ArrayList<ChatMsg> getByRoomAccountId(String roomAccountId) {
        ArrayList<ChatMsg> chatMsgList=new ArrayList<>();
        ChatMsg chatMsg = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select room_msg_id,room.room_id,user_id,date,content from chat_msg,room where chat_msg.room_id =room.room_id and room.room_account_id ='" + roomAccountId+"'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                chatMsg = new ChatMsg();
                chatMsg.setRoomMsgId(rs.getInt(1));
                chatMsg.setRoomId(rs.getInt(2));
                chatMsg.setUserId(rs.getInt(3));
                chatMsg.setDate(rs.getTimestamp(4));
                chatMsg.setContent(rs.getString(5));
                chatMsgList.add(chatMsg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatMsgList;
    }
}
