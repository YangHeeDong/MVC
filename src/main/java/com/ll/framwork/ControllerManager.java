package com.ll.framwork;

import com.ll.App;
import com.ll.framwork.annotataion.*;
import com.ll.base.rq.Rq;
import com.ll.myMap.MyMap;
import com.ll.util.Ut;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerManager {
    private static Map<String, RouteInfo> routeInfos;

    static{
        routeInfos = new HashMap<>();

        scanMappings();
    }

    private static void scanMappings() {
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);


        for (Class<?> controllerCls : ref.getTypesAnnotatedWith(Controller.class)) {
            Method[] methods = controllerCls.getDeclaredMethods();

            for (Method method : methods) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                PutMapping putMapping = method.getAnnotation(PutMapping.class);

                String httpMethod = null;
                String path = null;

                if (getMapping != null) {
                    path = getMapping.value();
                    httpMethod = "GET";
                } else if (postMapping != null) {
                    path = postMapping.value();
                    httpMethod = "POST";
                } else if (deleteMapping != null) {
                    path = deleteMapping.value();
                    httpMethod = "DELETE";
                } else if (putMapping != null) {
                    path = putMapping.value();
                    httpMethod = "PUT";
                }

                // 여기도 왜 matchCount가 고정?
                if (path != null && httpMethod != null) {
                    String actionPath = Ut.str.beforeFrom(path, "/", 3);
                    // String actionPath = Ut.str.beforeFrom(path, "/");
                    //String actionPath = path;

                    String key = httpMethod + "___" + actionPath;
                    //String key = httpMethod + "___" + path;

                    routeInfos.put(key, new RouteInfo(path, actionPath, controllerCls, method));
                }
            }
        }
    }


    public static void runAction(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);

        String routeMethod = rq.getRouteMethod();
        String actionPath = rq.getActionPath();

        String mappingKey = routeMethod + "___" + actionPath;

        boolean contains = routeInfos.containsKey(mappingKey);

        if (contains == false) {
            rq.println("해당 요청은 존재하지 않습니다.");
            return;
        }

        if(Integer.parseInt(rq.getSessionAttributeByKey("loginedMemberId")) == -1){
            if(
                    actionPath.equals("/article/create") ||
                    actionPath.equals("/article/modify") ||
                    actionPath.equals("/reply/create") ||
                    actionPath.equals("/reply/modify") ||
                    actionPath.equals("/member/changePassword")
            ){
                mappingKey = routeMethod+"___/member/login";
            }

        }

        RouteInfo routeInfo = routeInfos.get(mappingKey);
        rq.setRouteInfo(routeInfo);

        runAction(rq);
    }

    private static void runAction(Rq rq) {
        RouteInfo routeInfo = rq.getRouteInfo();
        Class controllerCls = routeInfo.getControllerCls();
        Method actionMethod = routeInfo.getMethod();

        Object controllerObj = Container.getObj(controllerCls);

        try {
            // invoke 시간 딜레이를 주고 실행하는 함순디
            // 왜하는거지
            actionMethod.invoke(controllerObj, rq);
        } catch (IllegalAccessException e) {
            rq.println("IllegalAccessException 액션시작에 실패하였습니다.");
        } catch (InvocationTargetException e) {
            rq.println("InvocationTargetException 액션시작에 실패하였습니다.");
            throw new RuntimeException(e);
        } finally {
            // try문 후에 실행
             MyMap myMap = Container.getObj(MyMap.class);
             myMap.closeConnection(); // 현재 쓰레드에 할당된 커넥션을 닫는다.

            // 안하면 벌어지는 일
            // 매 요청마다, DB 요청이 쌓인다.
        }

    }


}
