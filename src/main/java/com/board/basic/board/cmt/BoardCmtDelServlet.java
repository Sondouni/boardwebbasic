package com.board.basic.board.cmt;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardCmtDTO;
import com.board.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/del")
public class BoardCmtDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardCmtDTO dto = new BoardCmtDTO();
        dto.setIcmt(MyUtils.getParameterInt(req,"icmt"));
        int result = BoardCmtDAO.delCmt(dto);
        res.sendRedirect("/board/detail?iboard="+req.getParameter("iboard"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
