package com.board.basic.user;

import com.board.basic.MyUtils;
import com.board.basic.dao.UserDAO;
import com.board.basic.user.model.LoginResult;
import com.board.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.displayView("login","user/login",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserEntity un = new UserEntity();
        un.setUid(req.getParameter("uid"));
        un.setUpw(req.getParameter("upw"));
        System.out.println(un);

        LoginResult lr = UserDAO.logIn(un);
        System.out.println(lr.getResult());
        System.out.println(lr.getLoginUser());

        String err = null;
        //새로운 login 메소드 err 분기
        /*
        UserEntity userEntity = UserDAO.selUserEntity(un);
        if(userEntity==null){
            err = " check your id ";
        }else {
            String dbPw = userEntity.getUpw();
            if(BCrypt.checkpw(un.getUpw(),dbPw)){
                userEntity.setUpw(null);
                HttpSession session = req.getSession();
                session.setAttribute("login",userEntity);
                res.sendRedirect("/board/list");
                return;
            }else {
                err = " check your pw ";
            }
        }

         */



        switch (lr.getResult()){
            case 1:
                HttpSession session = req.getSession();
                session.setAttribute("loginUser",lr.getLoginUser());
                res.sendRedirect("/board/list");
                return;
            default:
                switch (lr.getResult()){
                    case 0:
                        err="fail to login";
                        break;
                    case 2:
                        err="check your id";
                        break;
                    case 3:
                        err="check your pw";
                        break;
                }
                req.setAttribute("err",err);
                doGet(req,res);
                return;
        }
    }
}
