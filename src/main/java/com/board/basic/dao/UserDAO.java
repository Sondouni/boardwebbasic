package com.board.basic.dao;

import com.board.basic.DButils;
import com.board.basic.user.model.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public static int join(UserEntity param){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " INSERT into t_user "+
                " (uid, upw, nm, gender) "+
                " VALUES "+
                " (?,?,?,?) ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,param.getUid());
            pr.setString(2,param.getUpw());
            pr.setString(3,param.getNm());
            pr.setInt(4,param.getGender());
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
