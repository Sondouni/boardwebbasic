package com.board.basic.dao;

import com.board.basic.DButils;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.board.model.BoardHeartEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardHeartDAO {
    public static int selIsHeart(BoardHeartEntity entity){
        int result = 0;
        Connection con  = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT * from t_board_heart WHERE iboard = ? AND iuser = ?";
        try {
            con  = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,entity.getIboard());
            pr.setInt(2,entity.getIuser());
            rs = pr.executeQuery();
            if(rs.next()){
                result = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return result;
    }
    public static int insBoardHeart(BoardHeartEntity entity){
        int result = 0;
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " INSERT into t_board_heart (iuser, iboard) VALUES (?,?) ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,entity.getIuser());
            pr.setInt(2,entity.getIboard());
            result = pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return result;
    }
    public static int delBoardHeart(BoardHeartEntity entity){
        int result = 0;
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " DELETE from t_board_heart where iuser = ? and iboard = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,entity.getIuser());
            pr.setInt(2,entity.getIboard());
            result = pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return result;
    }
    public static int selCountHeart(BoardHeartEntity entity){
        int result = 0;
        Connection con  = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT COUNT(*) FROM t_board_heart WHERE iboard = ?";
        try {
            con  = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,entity.getIboard());
            rs = pr.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return result;
    }
}
