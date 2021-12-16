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

@WebServlet("/user/password")
public class UserPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "change Password";
        req.setAttribute("subPage","user/password");
        MyUtils.displayView(title,"user/myPage",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //파라미터로 현재비밀번호,바뀐거 받아오기
        String curUpw = req.getParameter("upw");
//        String realUpw = MyUtils.getLoginUser(req).getUpw();
        UserEntity entity1 = new UserEntity();
        entity1.setIuser(MyUtils.getLoginUserPK(req));
        String realUpw = UserDAO.selUser(entity1).getUpw();//실제 비밀번호
        String newUpw = BCrypt.hashpw(req.getParameter("changedupw"),BCrypt.gensalt());
        if(!BCrypt.checkpw(curUpw,realUpw)){
            req.setAttribute("err","you typed wrong password");
            doGet(req,res);
            return;
        }else {
            UserEntity entity = new UserEntity();
            entity.setUpw(newUpw);
            entity.setIuser(MyUtils.getLoginUserPK(req));
            UserDAO.updUser(entity);
            res.sendRedirect("/user/logout");
            return;
        }

    }
}
