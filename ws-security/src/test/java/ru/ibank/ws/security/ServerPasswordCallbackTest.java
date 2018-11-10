package ru.ibank.ws.security;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.junit.Before;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;

import java.io.IOException;

import static org.junit.Assert.*;

public class ServerPasswordCallbackTest {

    private ServerPasswordCallback serverPasswordCallback;
    private final Callback[] callbacks = new Callback[1];
    private final WSPasswordCallback wsPasswordCallback = new WSPasswordCallback("joe",0);

    @Before
    public void init (){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/beans.xml",
                                                                                             "META-INF/spring/reference-beans.xml"});
        serverPasswordCallback = ctx.getBean("passwordCallback", ServerPasswordCallback.class);
        callbacks[0] = wsPasswordCallback;
    }

    @Test
    public void handle() {

        try {
            serverPasswordCallback.handle(callbacks);
            assertEquals("123", wsPasswordCallback.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

    }
}