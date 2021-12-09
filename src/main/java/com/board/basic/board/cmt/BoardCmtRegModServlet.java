package com.board.basic.board.cmt;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardCmtEntity;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.dao.BoardCmtDAO;
import com.board.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/reg")
public class BoardCmtRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /*
        int iboard = MyUtils.getParameterInt(req,"iboard");
        if(iboard>0){
            BoardDTO dto = new BoardDTO();
            dto.setIboard(iboard);
            req.setAttribute("data", BoardDAO.selOne(dto));
        }
        MyUtils.displayView("article","board/regmod",req,res);

         */
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(MyUtils.getParameterInt(req,"iboard"));
        entity.setCtnt(req.getParameter("ctnt"));
        entity.setWriter(MyUtils.getLoginUserPK(req));
        int icmt = MyUtils.getParameterInt(req,"icmt");
        int result = 0;
        if(icmt>0){
            entity.setIcmt(icmt);
            result = BoardCmtDAO.updCmt(entity);
        }else if(icmt==0){
            result = BoardCmtDAO.insBoardCmt(entity);
        }
        switch (result){
            case 1:
                req.setAttribute("check","not null");
                res.sendRedirect("/board/detail?iboard="+entity.getIboard());
            default:
                break;
        }
    }
}
