package ru.ibank.ws.security;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ServerPasswordCallback implements CallbackHandler {

    private final Logger logger = LoggerFactory.getLogger(ServerPasswordCallback.class);
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
        } catch (java.lang.Exception e) {
            logger.error("Could not get password");
        } finally {
            sqlSession.close();
        }
    }
}