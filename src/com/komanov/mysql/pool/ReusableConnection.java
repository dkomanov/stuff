package com.komanov.mysql.pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class ReusableConnection implements Connection {

    final Connection underlying;
    int counter = 0;

    private static class NetworkTimeout {
        final Executor executor;
        final int timeout;

        private NetworkTimeout(Executor executor, int timeout) {
            this.executor = executor;
            this.timeout = timeout;
        }
    }

    boolean dirtyCommit = false;

    Boolean initialAutoCommit = null;
    Boolean initialReadOnly = null;
    String initialCatalog = null;
    String initialSchema = null;
    Integer initialTransactionIsolation = null;
    NetworkTimeout initialNetworkTimeout = null;

    private void markCommitDirty() throws SQLException {
        if (!getAutoCommit()) {
            dirtyCommit = true;
        }
    }

    ReusableConnection(Connection underlying) {
        this.underlying = underlying;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return underlying.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return underlying.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return underlying.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return underlying.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        setInitial(() -> initialAutoCommit, Connection::getAutoCommit, v -> initialAutoCommit = v);
        underlying.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return underlying.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        dirtyCommit = false;
        underlying.commit();
    }

    @Override
    public void rollback() throws SQLException {
        dirtyCommit = false;
        underlying.rollback();
    }

    @Override
    public void close() throws SQLException {
        --counter;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return underlying.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        markCommitDirty();
        return underlying.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        setInitial(() -> initialReadOnly, Connection::isReadOnly, v -> initialReadOnly = v);
        dirtyCommit = false;
        underlying.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return underlying.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        setInitial(() -> initialCatalog, Connection::getCatalog, v -> initialCatalog = v);
        underlying.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return underlying.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        setInitial(() -> initialTransactionIsolation, Connection::getTransactionIsolation, v -> initialTransactionIsolation = v);
        underlying.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return underlying.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return underlying.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        underlying.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return underlying.createStatement(resultSetType, resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return underlying.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return underlying.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return underlying.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        underlying.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        underlying.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return underlying.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return underlying.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return underlying.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        dirtyCommit = false;
        underlying.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        underlying.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return underlying.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return underlying.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return underlying.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return underlying.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return underlying.prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return underlying.prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return underlying.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return underlying.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return underlying.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return underlying.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return underlying.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        underlying.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        underlying.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return underlying.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return underlying.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return underlying.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return underlying.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        setInitial(() -> initialSchema, Connection::getSchema, v -> initialSchema = v);
        underlying.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return underlying.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        underlying.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        setInitial(() -> initialNetworkTimeout, c -> new NetworkTimeout(executor, c.getNetworkTimeout()), v -> initialNetworkTimeout = v);
        underlying.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return underlying.getNetworkTimeout();
    }

    @Override
    public void beginRequest() throws SQLException {
        underlying.beginRequest();
    }

    @Override
    public void endRequest() throws SQLException {
        underlying.endRequest();
    }

    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout) throws SQLException {
        return underlying.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
    }

    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
        return underlying.setShardingKeyIfValid(shardingKey, timeout);
    }

    @Override
    public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
        underlying.setShardingKey(shardingKey, superShardingKey);
    }

    @Override
    public void setShardingKey(ShardingKey shardingKey) throws SQLException {
        underlying.setShardingKey(shardingKey);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return underlying.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return underlying.isWrapperFor(iface);
    }

    private <T> void setInitial(Supplier<T> getter, UncheckedFunction<Connection, T> f, Consumer<T> setter) throws SQLException {
        if (getter.get() == null) {
            setter.accept(f.applyChecked(underlying));
        }
    }

    private interface UncheckedFunction<T, R> extends Function<T, R> {
        R applyChecked(T t) throws SQLException;

        @Override
        default R apply(T t) {
            try {
                return applyChecked(t);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
