package com.board.basic.user;

import com.board.basic.FileUtils;
import com.board.basic.MyUtils;
import com.board.basic.dao.UserDAO;
import com.board.basic.user.model.UserEntity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.mindrot.jbcrypt.BCrypt;

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
        int loginUserPk = MyUtils.getLoginUserPK(req);
        UserEntity entity = new UserEntity();
        entity.setIuser(loginUserPk);
        req.setAttribute("data",UserDAO.selUser(entity));

        String title = "proFile";
        req.setAttribute("subPage","user/profile");
        MyUtils.displayView(title,"user/myPage",req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = MyUtils.getLoginUserPK(req);
        int maxSize = 10_485_760; // 파일의 최대 크기를 정해줌, 1024*1024*10 10mb

        ServletContext app = req.getServletContext(); // 어플리케이션 객체(servlet context의 객체, 이름이 어플리케이션이라 불림)
        String targetPath = app.getRealPath("/res/img/profile/"+loginUserPk);//톰켓에서 돌아가는 프로젝트 root경로값을 문자열로 준다
        //톰켓이 들어있는 폴더에서, 경로를 더욱 추가 path=경로
        String encoding = "UTF-8"; // 인코딩은 한글

        //이미지파일이 하나만 등록되도록(전에 있던파일 삭제)
        File targetFolder = new File(targetPath);//폴더 생성
        if(targetFolder.exists()){
            FileUtils.delFolderFiles(targetPath,false);
        }else {
            targetFolder.mkdirs();//mkdir는 경로중 하나라도 폴더가 없을시 에러가 터지지만, mkdirs는 폴더를 생성해준다다
        }

        System.out.println("targetPath : "+targetPath);
        MultipartRequest mr = new MultipartRequest(req,targetPath,maxSize,encoding, new DefaultFileRenamePolicy());
        //mulitpartRequest를 생성하는데 필요한것 : req, 경로, 최대용량, 인코딩, 파일이름 중복시 처리해주는 객체

        Enumeration enumFiles = mr.getFileNames(); //ResultSet과 비슷한 개념,
        String changedFileNm = mr.getOriginalFileName("profileImg");
        /*
        if(enumFiles.hasMoreElements()){
            String originFileNm = (String)enumFiles.nextElement();
            System.out.println("originFileNm : "+originFileNm);
            String changedFileNm = mr.getOriginalFileName(originFileNm);
            System.out.println("originFileNm : "+changedFileNm);
            String changedFileNm1 =  mr.getFilesystemName(originFileNm);
            System.out.println("changedFileNm : "+changedFileNm1);
        }

         */

        UserEntity entity = new UserEntity();
        entity.setIuser(MyUtils.getLoginUserPK(req));
        entity.setProfileImg(changedFileNm);
        entity.setUpw(BCrypt.hashpw((String)req.getAttribute("upw"),BCrypt.gensalt()));
        int result = UserDAO.updUser(entity);
        //doGEt을 하게된다면, 마지막에 동작한것이 post일때 새로고침을 하게되면 다시 post로 날리게 된다.(새로고침은 제일 마지막 동작을 한번 더 하는것)
        //그래서 sendRediect 를 이용한다
        res.sendRedirect("/user/profile");
    }
}
