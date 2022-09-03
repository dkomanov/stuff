package com.komanov.mysql.hashset.perf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

class ThreadLocalConnectionPool implements ConnectionPool {
    private static final ThreadLocal<Connection> tl = new ThreadLocal<>();
    private final String url;
    private final List<Connection> connections = new ArrayList<>();

    public ThreadLocalConnectionPool(String url) {
        this.url = url;
    }

    @Override
    public Connection acquire() throws Throwable {
        Connection conn = tl.get();
        if (conn == null) {
            conn = DriverManager.getConnection(url);
            connections.add(conn);
            tl.set(conn);
        }
        return conn;
    }

    @Override
    public void release(Connection conn) throws Throwable {
        // do nothing, we clean up only on shutdown.
    }

    @Override
    public void shutdown() throws Throwable {
        for (Connection conn : connections) {
            conn.close();
        }
    }
}
