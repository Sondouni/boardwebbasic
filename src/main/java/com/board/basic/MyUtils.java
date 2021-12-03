package com.board.basic;

import com.board.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyUtils {
    public static void displayView(String title, String view, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("title",title);
        req.setAttribute("page",view);
        disForward(req,res,"layout");
    }
    public static void disForward(HttpServletRequest req,HttpServletResponse res, String jsp)throws ServletException,IOException{
        req.getRequestDispatcher("/WEB-INF/view/"+jsp+".jsp").forward(req,res);
    }
    public static int getParameterInt(HttpServletRequest req,String str){
        return parseStringToInt(req.getParameter(str),0);
    }
    public static int parseStringToInt(String val,int defVal){
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {}
        return defVal;
    }
    public static UserEntity getLoginUser(HttpServletRequest req){
        HttpSession session = req.getSession();
        return  (UserEntity)session.getAttribute("loginUser");
    }
    public static int getLoginUserPK(HttpServletRequest req){
        UserEntity entity = getLoginUser(req);
        if(entity==null){
            return 0;
        }
        return entity.getIuser();
//        return entity==null?0:entity.getIuser();
    }
}
