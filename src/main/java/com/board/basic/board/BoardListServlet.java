package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //search
        String abcc = req.getParameter("abc");
        int searchType = MyUtils.getParameterInt(req,"searchType");
        String searchText = req.getParameter("searchText");
        //dto 세팅
        BoardDTO dto = new BoardDTO();
        dto.setRowCnt(5);
        dto.setSearchText(searchText);
        dto.setSearchType(searchType);
        int maxPagenum = BoardDAO.getMaxPageNum(dto);

        req.setAttribute("maxPagenum",maxPagenum);
        dto.setPage(MyUtils.getParameterInt(req,"page",1));
        //list뿌리기
        if(maxPagenum>0){
            req.setAttribute("list", BoardDAO.selBoardList(dto));
        }
        MyUtils.displayView("Board","/board/list",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
