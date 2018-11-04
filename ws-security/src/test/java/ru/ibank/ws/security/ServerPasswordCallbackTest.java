package ru.ibank.ws.security;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ServerPasswordCallbackTest {

    private static ClassPathXmlApplicationContext ctx;

    public static void init (){

        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/beans.xml",
                                                              "META-INF/spring/ref-beans.xml",});

    }

    @Test
    public void handle() {


    }
}