package com.board.basic.dao;

import com.board.basic.DButils;
import com.board.basic.board.model.BoardCmtDTO;
import com.board.basic.board.model.BoardCmtEntity;
import com.board.basic.board.model.BoardCmtVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardCmtDAO {
    public static int insBoardCmt(BoardCmtEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " INSERT into t_board_cmt "+
                " (iboard, ctnt, writer) "+
                " values "+
                " (?, ? ,? ) ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,entity.getIboard());
            pr.setString(2,entity.getCtnt());
            pr.setInt(3,entity.getWriter());
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return 0;
    }
    public static List<BoardCmtVO> boardCmtList(BoardCmtDTO dto){
        List<BoardCmtVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT A.icmt,A.ctnt,A.writer,A.rdt, B.nm as writeNm from t_board_cmt A inner join t_user B " +
                " on A.writer = B.iuser "+
                " where A.iboard = ? "+
                " ORDER BY A.icmt DESC ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,dto.getIboard());
            rs = pr.executeQuery();
            while(rs.next()){
                BoardCmtVO vo = new BoardCmtVO();
                vo.setIcmt(rs.getInt("icmt"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriterNm(rs.getString("writeNm"));
                vo.setRdt(rs.getString("rdt"));
                vo.setWriter(rs.getInt("writer"));
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return list;
    }
    public static int delCmt(BoardCmtDTO dto){
        Connection con =null;
        PreparedStatement pr = null;
        String sql = " DELETE from t_board_cmt where icmt = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,dto.getIcmt());
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return 0;
    }
    public static int updCmt(BoardCmtEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " UPDATE t_board_cmt set ctnt = ? where icmt = ? and writer = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,entity.getCtnt());
            pr.setInt(2,entity.getIcmt());
            pr.setInt(3,entity.getWriter());
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return 0;
    }
}
