package com.komanov.mysql.hashset.perf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

class HikariConnectionPool implements ConnectionPool {
    private final HikariDataSource ds;

    HikariConnectionPool(String url) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setMaximumPoolSize(PerfTester.MaxParallelism);
        config.setMinimumIdle(PerfTester.MaxParallelism);
        this.ds = new HikariDataSource(config);
    }

    @Override
    public Connection acquire() throws Throwable {
        return ds.getConnection();
    }

    @Override
    public void release(Connection conn) throws Throwable {
        conn.close();
    }

    @Override
    public void shutdown() throws Throwable {
        ds.close();
    }
}
