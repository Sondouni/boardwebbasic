package com.board.basic.board;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardCmtDTO;
import com.board.basic.board.model.BoardDTO;
import com.board.basic.board.model.BoardHeartEntity;
import com.board.basic.board.model.BoardVO;
import com.board.basic.dao.BoardCmtDAO;
import com.board.basic.dao.BoardDAO;
import com.board.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail2")
public class BoardDetailServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BoardDTO dto = new BoardDTO();
        dto.setIboard(MyUtils.getParameterInt(req,"iboard"));
        //heart
        int iuser = MyUtils.getLoginUserPK(req);
        if (iuser>0){
            BoardHeartEntity entity = new BoardHeartEntity();
            entity.setIuser(iuser);
            entity.setIboard(MyUtils.getParameterInt(req,"iboard"));
            req.setAttribute("isHeart", BoardHeartDAO.selIsHeart(entity));
        }
        //hit
        if((req.getParameter("check")==null)&&(MyUtils.getLoginUser(req)==null ||
                MyUtils.getLoginUser(req).getIuser()!=MyUtils.getParameterInt(req,"writer"))){
            BoardDAO.hitup(dto);
            System.out.println("성공");
        }
        BoardVO vo = BoardDAO.selOne(dto);

        //댓글은 ajax로


        //세팅
        req.setAttribute("data",vo);
        MyUtils.displayView("article","/board/detail2",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
