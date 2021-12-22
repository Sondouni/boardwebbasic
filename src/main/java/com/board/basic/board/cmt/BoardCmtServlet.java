package com.board.basic.board.cmt;

import com.board.basic.MyUtils;
import com.board.basic.board.model.BoardCmtDTO;
import com.board.basic.board.model.BoardCmtEntity;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //proc로 수정,삭제,등록 분기
        String proc = req.getParameter("proc");

        String json = MyUtils.getJson(req);
        System.out.println("proc : "+ proc+ "\n"+"json : "+json);
        Gson gson = new Gson();
        //수정,등록
        BoardCmtEntity entity = gson.fromJson(json, BoardCmtEntity.class);
        entity.setWriter(MyUtils.getLoginUserPK(req));
        //삭제
        BoardCmtDTO dto = gson.fromJson(json, BoardCmtDTO.class);
        dto.setWriter(MyUtils.getLoginUserPK(req));

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        Map<String,Integer> insMap=null;
        int result = 0;
        switch (proc) {
            case "upd":
                result = BoardCmtDAO.updCmt(entity); // writer,icmt,ctnt
                break;
            case "del":
                result = BoardCmtDAO.delCmt(dto);
                break;
            case "ins":
                result = BoardCmtDAO.insBoardCmt(entity);
                insMap = new HashMap();
                insMap.put("icmt",entity.getIcmt());
                insMap.put("result",result);
                String mapJson =gson.toJson(insMap);
                System.out.println(mapJson);
                out.print(mapJson);
                break;

        }
        if(insMap==null){//print를 한번만 할수있는가..?
            out.print(String.format("{\"result\":%d}", result));
        }

        //Map으로 넣어놓고 가능
        Map<String,Integer> map = new HashMap();
        map.put("result",result);
        String resultJson = gson.toJson(map);
        //
    }
}
