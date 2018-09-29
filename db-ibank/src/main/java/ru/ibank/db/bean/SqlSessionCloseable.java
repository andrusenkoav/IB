package ru.ibank.db.bean;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.*;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class SqlSessionCloseable implements SqlSession, Closeable {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    public SqlSessionCloseable(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
        this.sqlSession = sqlSessionFactory.openSession(false);
    }

    public <T> T selectOne(String s) {
        return null;
    }

    public <T> T selectOne(String s, Object o) {
        return null;
    }

    public <E> List<E> selectList(String s) {
        return null;
    }

    public <E> List<E> selectList(String s, Object o) {
        return null;
    }

    public <E> List<E> selectList(String s, Object o, RowBounds rowBounds) {
        return null;
    }

    public <K, V> Map<K, V> selectMap(String s, String s1) {
        return null;
    }

    public <K, V> Map<K, V> selectMap(String s, Object o, String s1) {
        return null;
    }

    public <K, V> Map<K, V> selectMap(String s, Object o, String s1, RowBounds rowBounds) {
        return null;
    }

    public <T> Cursor<T> selectCursor(String s) {
        return null;
    }

    public <T> Cursor<T> selectCursor(String s, Object o) {
        return null;
    }

    public <T> Cursor<T> selectCursor(String s, Object o, RowBounds rowBounds) {
        return null;
    }

    public void select(String s, Object o, ResultHandler resultHandler) {

    }

    public void select(String s, ResultHandler resultHandler) {

    }

    public void select(String s, Object o, RowBounds rowBounds, ResultHandler resultHandler) {

    }

    public int insert(String s) {
        return 0;
    }

    public int insert(String s, Object o) {
        return 0;
    }

    public int update(String s) {
        return 0;
    }

    public int update(String s, Object o) {
        return 0;
    }

    public int delete(String s) {
        return 0;
    }

    public int delete(String s, Object o) {
        return 0;
    }

    public void commit() {

    }

    public void commit(boolean b) {

    }

    public void rollback() {

    }

    public void rollback(boolean b) {

    }

    public List<BatchResult> flushStatements() {
        return null;
    }

    public void close() {
        sqlSession.close();
    }

    public void clearCache() {

    }

    public Configuration getConfiguration() {
        return null;
    }

    public <T> T getMapper(Class<T> aClass) {
        return sqlSession.getMapper(aClass);
    }

    public Connection getConnection() {
        return null;
    }
}
