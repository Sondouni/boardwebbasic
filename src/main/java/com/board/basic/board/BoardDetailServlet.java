package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.board.model.BoardEntity;
import com.board.basic.board.model.BoardVO;
import com.board.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardDTO dto = new BoardDTO();
        dto.setIboard(MyUtils.getParameterInt(req,"iboard"));
        //hit
        if(MyUtils.getLoginUser(req)==null ||
                MyUtils.getLoginUser(req).getIuser()!=MyUtils.getParameterInt(req,"writer")) {
            BoardDAO.hitup(dto);
            System.out.println("성공");
        }
        BoardVO vo = BoardDAO.selOne(dto);
        req.setAttribute("data",vo);
        MyUtils.displayView("article","/board/detail",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
