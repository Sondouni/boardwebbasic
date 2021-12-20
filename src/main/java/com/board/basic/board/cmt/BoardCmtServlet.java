package com.board.basic.board.cmt;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardCmtDTO;
import com.board.basic.board.model.BoardCmtVO;
import com.board.basic.dao.BoardCmtDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/board/cmt")
public class BoardCmtServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //리스트 뿌리기
        int iboard = MyUtils.getParameterInt(req,"iboard");
        BoardCmtDTO cmtDTO = new BoardCmtDTO();
        cmtDTO.setIboard(iboard);
        List<BoardCmtVO> list = BoardCmtDAO.boardCmtList(cmtDTO);
        //Json으로 js와 통신하기위해 Gson라이브러리가 필요
        Gson gson = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.print(gson.toJson(list));
        out.flush();
        //list를 json으로 변환
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //수정,삭제,등록
    }
}
