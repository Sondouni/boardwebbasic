package com.board.basic.dao;

import com.board.basic.DButils;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.board.model.BoardEntity;
import com.board.basic.board.model.BoardVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    //list limit를 걸어주는데, _,_에서 첫번째는 어디서부터 나타날지(startIdx), 몇개 나타날지(RowCnt)
    public static int getMaxPageNum(BoardDTO dto){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT CEIL(COUNT(*)/?) from t_board ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,dto.getRowCnt());
            rs = pr.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return 0;
    }

    public static int chgBoard(BoardEntity vo){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " UPDATE t_board set title = ?, ctnt = ?, mdt = now() where iboard = ? and writer = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,vo.getTitle());
            pr.setString(2,vo.getCtnt());
            pr.setInt(3,vo.getIboard());
            pr.setInt(4,vo.getWriter());
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

    public static int delOne(BoardEntity dto){
        Connection con  = null;
        PreparedStatement pr = null;
        String sql = " Delete from t_board where iboard = ? and writer = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,dto.getIboard());
            pr.setInt(2,dto.getWriter());
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

    public static BoardVO selOne(BoardDTO dto){
        BoardVO vo = null;
        Connection con  = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT A.title,A.hit,A.ctnt,A.rdt,A.writer,A.mdt,B.uid as writerNm from t_board A Inner join t_user B On A.writer = B.iuser where iboard = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,dto.getIboard());
            rs = pr.executeQuery();
            if(rs.next()){
                vo = BoardVO.builder().iboard(dto.getIboard())
                        .writer(rs.getInt("writer"))
                        .title(rs.getString("title"))
                        .hit(rs.getInt("hit"))
                        .ctnt(rs.getString("ctnt"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .mdt(rs.getString("mdt"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return vo;
    }
    public static void hitup(BoardDTO param){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " update t_board set hit = hit+1 where iboard = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,param.getIboard());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
    }

    public static int insPK(BoardEntity entity){
        int result = 0;
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " INSERT into t_board ( title, ctnt, writer ) values ( ? , ? , ? ) ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,entity.getTitle());
            pr.setString(2,entity.getCtnt());
            pr.setInt(3,entity.getWriter());
            result = pr.executeUpdate();
            rs = pr.getGeneratedKeys();
            if(rs.next()){
                entity.setIboard(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return result;
    }

    public static int insBoardWithPK(BoardEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " INSERT into t_board ( title, ctnt, writer ) values ( ? , ? , ? ) ";
        String sqlPK = " SELECT @@IDENTITY as iboard ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,entity.getTitle());
            pr.setString(2,entity.getCtnt());
            pr.setInt(3,entity.getWriter());
            //pk얻어오기
            pr.executeUpdate();
            pr = con.prepareStatement(sqlPK);
            rs = pr.executeQuery();
            if(rs.next()){
                entity.setIboard(rs.getInt("iboard"));
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr);
        }
        return 0;
    }

    public static int insBoard(BoardEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " INSERT into t_board ( title, ctnt, writer ) values ( ? , ? , ? ) ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,entity.getTitle());
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
    public static List<BoardVO> selBoardList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard,A.title,A.writer,A.hit,A.rdt,A.mdt,B.nm as writerNm "+
                " From t_board A "+
                " INNER JOIN t_user B "+
                " On A.writer = B.iuser "+
                " ORDER BY A.iboard DESC ";
        try {
            con = DButils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
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
            DButils.close(con,ps,rs);
        }
        return list;
    }
}
