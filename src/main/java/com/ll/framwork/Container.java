package com.ll.framwork;

import com.ll.App;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.Repository;
import com.ll.framwork.annotataion.Service;
import com.ll.myMap.MyMap;
import com.ll.util.Ut;

import org.reflections.Reflections;

import java.util.Arrays;
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
        scanCustom();

        // 레고 조립
        resolveDependenciesAllComponents();

    }
    
    // 아래 두 메서드 강의 들어보기
    private static void resolveDependenciesAllComponents() {
        for (Class cls : objects.keySet()) {
            Object o = objects.get(cls);

            resolveDependencies(o);
        }
    }
    
    private static void resolveDependencies(Object o) {
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .map(field -> {
                    field.setAccessible(true);
                    return field;
                })
                .forEach(field -> {
                    Class cls = field.getType();
                    Object dependency = objects.get(cls);

                    try {
                        field.set(o, dependency);
                    } catch (IllegalAccessException e) {

                    }
                });
    }

    private static void scanCustom() {
        objects.put(MyMap.class, new MyMap(App.DB_HOST, App.DB_PORT, App.DB_NAME, App.DB_ID, App.DB_PASSWORD));
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
