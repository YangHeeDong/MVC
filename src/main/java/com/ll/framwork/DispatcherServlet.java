package com.ll.framwork;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// @WebServlet("/article/*")
@WebServlet(urlPatterns = {"/article/*", "/member/*","/reply/*"})
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        long loginedMemberId = -1;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginedMemberId") != null){
            loginedMemberId = (long)session.getAttribute("loginedMemberId");
        }

        session.setAttribute("loginedMemberId",loginedMemberId);

        ControllerManager.runAction(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        doGet(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        doGet(req,resp);
    }

}
