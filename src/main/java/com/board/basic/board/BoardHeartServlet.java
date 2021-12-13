package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardHeartEntity;
import com.board.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/heart")
public class BoardHeartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int proc = MyUtils.getParameterInt(req,"proc");
        BoardHeartEntity entity = new BoardHeartEntity();
        entity.setIboard(MyUtils.getParameterInt(req,"iboard"));
        entity.setIuser(MyUtils.getLoginUserPK(req));
        if(proc==1){
            BoardHeartDAO.insBoardHeart(entity);
            res.sendRedirect("/board/detail?iboard="+entity.getIboard());
            return;
        }else if(proc==2){
            BoardHeartDAO.delBoardHeart(entity);
            res.sendRedirect("/board/detail?iboard="+entity.getIboard());
            return;
        }else {
            res.sendRedirect("/board/detail?iboard="+entity.getIboard());
        }
    }
}
