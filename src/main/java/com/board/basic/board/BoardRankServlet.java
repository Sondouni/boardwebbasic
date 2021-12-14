package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardVO;
import com.board.basic.dao.BoardRankDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board/rank")
public class BoardRankServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = req.getParameter("type");
        String title = "";
        List<BoardVO> list = null;
        switch (type){
            case "1":
                title = " top 10 hits ";
                list = BoardRankDAO.selBoardHitsRankList();
                break;
            case "2":
                title = " top 10 likes ";
                list = BoardRankDAO.selBoardLikesRankList();
                break;
            case "3":
                title = " top 10 comments" ;
                list = BoardRankDAO.selBoardCommentsRankList();
                break;
            default:
                break;
        }
        req.setAttribute("list",list);
        MyUtils.displayView(title,"board/rank",req,res);
    }
}
