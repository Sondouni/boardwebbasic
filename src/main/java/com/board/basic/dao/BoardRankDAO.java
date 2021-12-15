package com.board.basic.dao;

import com.board.basic.DButils;
import com.board.basic.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardRankDAO {
    public static List<BoardVO> selBoardHitsRankList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard,A.title,A.writer,A.hit,A.rdt,A.mdt,B.nm as writerNm from t_board A  "+
                " INNER JOIN t_user B "+
                " On A.writer = B.iuser "+
                " where hit>0 "+
                " order by hit desc, iboard desc limit 10 ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()){
                BoardVO vo = BoardVO.builder().iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .hit(rs.getInt("hit"))
                        .rdt(rs.getString("rdt"))
                        .mdt(rs.getString("mdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
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
    public static List<BoardVO> selBoardLikesRankList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard,A.title,A.writer,A.hit,A.rdt,A.mdt,C.nm as writerNm, COUNT(B.iuser) AS rank from t_board A  "+
                " INNER JOIN t_board_heart B "+
                " On A.iboard = B.iboard "+
                " INNER JOIN t_user C "+
                " ON A.writer = C.iuser "+
                " GROUP BY iboard "+
                " order by rank desc, iboard desc limit 10 ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()){
                BoardVO vo = BoardVO.builder().iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .hit(rs.getInt("hit"))
                        .rdt(rs.getString("rdt"))
                        .mdt(rs.getString("mdt"))
                        .writerNm(rs.getString("writerNm"))
                        .heartNum(rs.getInt("rank"))
                        .build();
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
    public static List<BoardVO> selBoardCommentsRankList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard,A.title,A.writer,A.hit,A.rdt,A.mdt,C.nm as writerNm, COUNT(B.icmt) AS rank from t_board A  "+
                " INNER JOIN t_board_cmt B "+
                " On A.iboard = B.iboard "+
                " INNER JOIN t_user C "+
                " ON A.writer = C.iuser "+
                " GROUP BY iboard "+
                " order by rank desc, iboard desc limit 10 ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()){
                BoardVO vo = BoardVO.builder().iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .hit(rs.getInt("hit"))
                        .rdt(rs.getString("rdt"))
                        .mdt(rs.getString("mdt"))
                        .writerNm(rs.getString("writerNm"))
                        .commentsNum(rs.getInt("rank"))
                        .build();
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
    public static List<BoardVO> selHitCntList(){
        String sql = " SELECT A.iboard,A.title,A.writer,A.hit as cnt,A.rdt,A.mdt,B.nm as writerNm from t_board A  "+
                " INNER JOIN t_user B "+
                " On A.writer = B.iuser "+
                " where hit>0 "+
                " order by hit desc, iboard desc limit 10 ";
        return procResultSet(sql);
    }
    public static List<BoardVO> selHeartCntList(){
        String sql = " SELECT A.iboard,A.title,A.writer,A.rdt,B.nm AS writerNm, C.cnt FROM t_board A "+
                " INNER JOIN t_user B "+
                " ON A.writer = B.iuser "+
                " INNER JOIN "+
                "(SELECT iboard,COUNT(iuser) AS cnt FROM t_board_heart GROUP BY iboard) C "+
                " ON A.iboard = C.iboard "+
                " order by rank desc, A.iboard desc limit 10 ";
        return procResultSet(sql);
    }
    public static List<BoardVO> selCmtCntList(){
        String sql = " SELECT A.iboard,A.title,A.writer,A.rdt,B.nm AS writerNm, C.cnt FROM t_board A "+
                " INNER JOIN t_user B "+
                " ON A.writer = B.iuser "+
                " INNER JOIN "+
                "(SELECT iboard,COUNT(icmt) AS cnt FROM t_board_cmt GROUP BY iboard) C "+
                " ON A.iboard = C.iboard "+
                " order by rank desc, A.iboard desc limit 10 ";
        return procResultSet(sql);
    }
    public static List<BoardVO> procResultSet(String sql){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()){
                BoardVO vo = BoardVO.builder().iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .mdt(rs.getString("mdt"))
                        .writerNm(rs.getString("writerNm"))
                        .cnt(rs.getInt("cnt"))
                        .build();
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
}
