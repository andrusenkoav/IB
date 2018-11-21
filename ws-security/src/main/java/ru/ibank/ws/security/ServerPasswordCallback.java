package ru.ibank.ws.security;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ServerPasswordCallback implements CallbackHandler {

    private final SqlSessionFactory sqlSessionFactory;

    public ServerPasswordCallback(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void handle(Callback[] callbacks) throws java.io.IOException, UnsupportedCallbackException {

        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            String password = sqlSession.selectOne("otpUserService.getPassword", pc.getIdentifier());
            if (password != null) {
                pc.setPassword(password);
            }
        } finally {
            sqlSession.close();
        }
    }
}