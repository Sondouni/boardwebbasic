package com.board.basic.board;

import com.board.basic.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/rank")
public class BoardRankServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String type = req.getParameter("type");
        String title = "";
        switch (type){
            case "1":
                title = " top 10 ranks ";
                break;
        }
        MyUtils.displayView(title,"board/rank",req,res);
    }
}
