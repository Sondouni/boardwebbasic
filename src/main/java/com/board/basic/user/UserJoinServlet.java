package com.board.basic.user;

import com.board.basic.MyUtils;
import com.board.basic.dao.UserDAO;
import com.board.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/join")
public class UserJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.displayView("join","user/join",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserEntity ue = new UserEntity();
        ue.setUid(req.getParameter("uid"));
        String pw = req.getParameter("upw");
        String hashPw = BCrypt.hashpw(pw,BCrypt.gensalt());
        ue.setUpw(hashPw);
//        ue.setUpw(BCrypt.hashpw(req.getParameter("upw"),BCrypt.gensalt()));
        ue.setNm(req.getParameter("nm"));
        ue.setGender(MyUtils.getParameterInt(req,"gender"));

        System.out.println(ue);

        int result = UserDAO.join(ue);
        switch (result){
            case 1:
                res.sendRedirect("login");
                break;
            default:
                req.setAttribute("err","fail to join");
                doGet(req,res);
                break;
        }
    }
}
