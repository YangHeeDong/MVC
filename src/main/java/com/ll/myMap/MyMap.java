package com.ll.myMap;

import lombok.Getter;
import lombok.Setter;

public class MyMap {
    @Getter
    @Setter
    private boolean isDevMode;
    private ConnectionPool connectionPool;

    public MyMap(String host, int port, String dbName, String username, String password) {
        connectionPool = new ConnectionPool(host, port,dbName, username, password);
    }

    public MyMap(String host, String dbName, String username, String password) {
        this(host, 3306, dbName, username, password);
    }

    public SecSql genSecSql() {
        return new SecSql(connectionPool, isDevMode);
    }


    public void closeConnection() {
        connectionPool.closeConnection();
    }

}
