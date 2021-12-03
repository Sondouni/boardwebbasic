package com.board.basic.dao;

import com.board.basic.DButils;
import com.board.basic.user.model.LoginResult;
import com.board.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static LoginResult logIn(UserEntity param){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        UserEntity un = new UserEntity();
        String sql = " SELECT iuser, upw, nm, gender from t_user where uid = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,param.getUid());
            rs = pr.executeQuery();
            if(rs.next()){
                if(BCrypt.checkpw(param.getUpw(),rs.getString("upw"))){
                    un.setUid(param.getUid());
                    un.setUpw(param.getUpw());
                    un.setNm(rs.getString("nm"));
                    un.setGender(rs.getInt("gender"));
                    un.setIuser(rs.getInt("iuser"));
                    return new LoginResult(1,un);
                }else {
                    return new LoginResult(3,un);
                }
            }else {
                return new LoginResult(2,un);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return new LoginResult(0,un);
    }
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
