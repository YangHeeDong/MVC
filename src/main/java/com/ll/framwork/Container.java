package com.ll.framwork;

import com.ll.App;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.Repository;
import com.ll.framwork.annotataion.Service;
import com.ll.util.Ut;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private static Map<Class, Object> objects;

    static{
        objects = new HashMap<>();
        scanComponents();
    }

    private static void scanComponents(){

        // 레고 생성이라는데
        scanRepositories();
        scanServices();
        scanControllers();

        // 레고 조립을 안해도 돌아가긴해
    }

    private static void scanRepositories(){
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        for(Class<?> cls : ref.getTypesAnnotatedWith(Repository.class)){
            objects.put(cls, Ut.cls.newObj(cls,null));
        }
    }

    private static void scanServices() {
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        for (Class<?> cls : ref.getTypesAnnotatedWith(Service.class)) {
            objects.put(cls, Ut.cls.newObj(cls, null));
        }
    }

    private static void scanControllers() {
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            objects.put(cls, Ut.cls.newObj(cls, null));
        }
    }

    public static <T> T getObj(Class<T> cls) {
        return (T) objects.get(cls);
    }

}
