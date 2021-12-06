package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.board.model.BoardEntity;
import com.board.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardEntity entity = new BoardEntity();
        entity.setIboard(MyUtils.getParameterInt(req,"iboard"));
        entity.setWriter(MyUtils.getLoginUserPK(req));
        int result = BoardDAO.delOne(entity);
        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                return;
            default:
                req.setAttribute("err","fail to delete");
                req.getRequestDispatcher("/board/detail?iboard="+MyUtils.getParameterInt(req,"iboard")).forward(req,res);
                return;
        }
    }
}
