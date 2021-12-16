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
    public static UserEntity selUser(UserEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT uid, nm,upw, gender, rdt, profileImg from t_user where iuser = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setInt(1,entity.getIuser());
            rs = pr.executeQuery();
            if(rs.next()){
                UserEntity userEntity = new UserEntity();
                userEntity.setUpw(rs.getString("upw"));
                userEntity.setUid(rs.getString("uid"));
                userEntity.setNm(rs.getString("nm"));
                userEntity.setGender(rs.getInt("gender"));
                userEntity.setRdt(rs.getString("rdt"));
                userEntity.setProfileImg(rs.getString("profileImg"));
                return userEntity;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DButils.close(con,pr,rs);
        }
        return null;
    }


    public static LoginResult logIn(UserEntity param){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        UserEntity un = new UserEntity();
        String sql = " SELECT iuser, upw, nm, gender,profileImg from t_user where uid = ? ";
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
                    un.setProfileImg(rs.getString("profileImg"));
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

    public static UserEntity selUserEntity(UserEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        UserEntity un = new UserEntity();
        String sql = " SELECT * from t_user where ";
        if(entity.getIuser()>0){
            sql += "iuser = "+entity.getIuser();
        }else {
            sql += "uid = '"+entity.getUid() +"'";
        }
        try {
            con=DButils.getCon();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            if(rs.next()){
                un.setIuser(rs.getInt("iuser"));
                un.setUid(rs.getString("uid"));
                un.setUpw(rs.getString("upw"));
                un.setNm(rs.getString("nm"));
                un.setGender(rs.getInt("gender"));
                un.setRdt(rs.getString("rdt"));
                un.setProfileImg(rs.getString("profileImg"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        return un;
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
    public static int updUser(UserEntity entity){
        Connection con = null;
        PreparedStatement pr = null;
        String sql = " UPDATE t_user set ";
        String changedVal = null;
        if(entity.getUpw()!=null&&!"".equals(entity.getUpw())){
            sql+= " upw = ? ";
            changedVal = entity.getUpw();
        }else if(entity.getProfileImg()!=null&&!"".equals(entity.getProfileImg())){
            sql+= " profileImg = ? ";
            changedVal = entity.getProfileImg();
        }
        sql += " Where iuser = ? ";
        try {
            con = DButils.getCon();
            pr = con.prepareStatement(sql);
            pr.setString(1,changedVal);
            pr.setInt(2,entity.getIuser());
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
