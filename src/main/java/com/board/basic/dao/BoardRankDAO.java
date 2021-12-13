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
}
