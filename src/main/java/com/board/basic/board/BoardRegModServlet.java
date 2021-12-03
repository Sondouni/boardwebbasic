package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardEntity;
import com.board.basic.dao.BoardDAO;
import com.board.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/regmod")
public class BoardRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.displayView("write","board/regmod",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardEntity be = new BoardEntity();
        be.setTitle(req.getParameter("title"));
        be.setCtnt(req.getParameter("ctnt"));
        be.setWriter(MyUtils.getLoginUserPK(req));

        System.out.println(be.getTitle());
        System.out.println(be.getCtnt());
        System.out.println(be.getWriter());

        int result = BoardDAO.insBoardWithPK(be);
        System.out.println(be.getIboard());
        //todo insert시 pk가 나왓을때 분기
        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                break;
            default:

                break;
        }
    }
}
