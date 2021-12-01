package com.board.basic;

import java.sql.*;

import static java.lang.Class.forName;

public class DButils {
    public static Connection getCon() throws SQLException,ClassNotFoundException {
        final String URL = "jdbc:mysql://localhost:3308/boardbasic";
        final String USER_NAME="root";
        final String PASSWORD="koreait";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        System.out.println("DB 연결 성공");
        return con;
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try { ps.close();  }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection con, PreparedStatement ps){
        close(con,ps,null);
    }
}
