package com.ll.base.rq;

import com.ll.framwork.RouteInfo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    @Setter
    @Getter
    private RouteInfo routeInfo; // 언제쓰는거여

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    public String getRouteMethod() {

        String method = getParam("_method", "");

        if (method.length() > 0) {
            return method.toUpperCase();
        }

        return req.getMethod();
    }

    public String getActionPath() {
        // URI /로 자를때 길이에 따라 error
        // 이걸 왜 하는 거지?
        // String[] bits = req.getRequestURI().split("/");

        // return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);

        return req.getRequestURI();
    }

    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);

        // 여기의 의미가 뭘까
        if (value == null) {
            value = getPathParam(paramName, null);
        }

        if (value == null || value.trim().length() == 0) {
            return defaultValue;
        }

        return value;
    }

    public String getPathParam(String paramName, String defaultValue) {
        if (routeInfo == null) {
            return defaultValue;
        }

        String path = routeInfo.getPath();

        String[] pathBits = path.split("/");

        int index = -1;

        for (int i = 0; i < pathBits.length; i++) {
            String pathBit = pathBits[i];

            if (pathBit.equals("{" + paramName + "}")) {
                index = i - 4;
                break;
            }
        }

        if (index != -1) {
            return getPathValueByIndex(index, defaultValue);
        }

        return defaultValue;
    }

    public String getPathValueByIndex(int index, String defaultValue) {
        String[] bits = req.getRequestURI().split("/");

        try {
            return bits[4 + index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return defaultValue;
        }
    }

    public void view(String path) {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/"+path + ".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void print(String str) {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void println(String str) {
        print(str + "\n");
    }

}
