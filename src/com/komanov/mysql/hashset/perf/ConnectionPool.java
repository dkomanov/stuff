package com.komanov.mysql.hashset.perf;

import java.sql.Connection;

interface ConnectionPool {
    Connection acquire() throws Throwable;

    void release(Connection conn) throws Throwable;

    void shutdown() throws Throwable;
}
