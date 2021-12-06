package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.board.model.BoardEntity;
import com.board.basic.board.model.BoardVO;
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
        String title = "write";
        int iboard = MyUtils.getParameterInt(req,"iboard");
        if(iboard>0){
            if(req.getParameter("data")==null) {
                title = "change";
                BoardDTO dto = new BoardDTO();
                dto.setIboard(iboard);
                BoardVO vo = BoardDAO.selOne(dto);
                req.setAttribute("data", vo);
            }
        }
        MyUtils.displayView(title,"board/regmod",req,res);
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
        int result=0;
        int iboard = MyUtils.getParameterInt(req,"iboard");
        if(iboard>0){
            be.setIboard(iboard);
            be.setWriter(MyUtils.getLoginUserPK(req));
            result = BoardDAO.chgBoard(be);
        }else if(iboard==0){
            result = BoardDAO.insPK(be);
        }
        /*
        if("change".equals(req.getParameter("submit"))){
            be.setIboard(MyUtils.getParameterInt(req,"iboard"));
            result = BoardDAO.chgBoard(be);
        }else {
            result = BoardDAO.insBoardWithPK(be);
        }
         */
        System.out.println(be.getIboard());
        //todo insert시 pk가 나왓을때 분기
        switch (result){
            case 1:
                if(be.getIboard()!=0){
                    res.sendRedirect("/board/detail?iboard="+be.getIboard());
                    return;
                }
            default:
                req.setAttribute("err","fail");
                req.setAttribute("data",be);
                doGet(req,res);
                return;
        }
    }
}
