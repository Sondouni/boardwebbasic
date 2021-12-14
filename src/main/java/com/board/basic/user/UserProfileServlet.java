package com.board.basic.user;

import com.board.basic.MyUtils;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/user/profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "proFile";
        req.setAttribute("subPage","user/profile");
        MyUtils.displayView(title,"user/myPage",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = MyUtils.getLoginUserPK(req);
        int maxSize = 10_485_760; // 파일의 최대 크기를 정해줌, 1024*1024*10 10mb

        ServletContext app = req.getServletContext();
        String targetPath = app.getRealPath("/res/img/profile/"+loginUserPk); //톰켓이 들어있는 폴더에서, 경로를 더욱 추가 path=경로
        String encoding = "UTF-8"; // 인코딩은 한글

        File file = new File(targetPath);//폴더 생성
        file.mkdirs();

        System.out.println("targetPath : "+targetPath);
        MultipartRequest mr = new MultipartRequest(req,targetPath,maxSize,encoding, new DefaultFileRenamePolicy());
        //mulitpartRequest를 생성하는데 필요한것 : req, 경로, 최대용량, 인코딩, 파일이름 중복시 처리해주는 객체

        Enumeration files = mr.getFileNames();
        String fileNm = (String)files.nextElement();
        System.out.println("fileNm : "+fileNm);
    }
}
