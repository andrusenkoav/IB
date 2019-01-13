package ru.ibank.ws.user.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserWSMockTest extends CamelSpringTestSupport {

    private final String ENDPOINT = "cxf:bean:userWS";
    private static UserDTO user;

    @EndpointInject(uri="mock:mybatis:ru.ibank.UserMapper.insert")
    MockEndpoint mockMybatisAddUser;

    @EndpointInject(uri="mock:mybatis:ru.ibank.UserMapper.findUserById")
    MockEndpoint mockMybatisFindUserById;

    @EndpointInject(uri="mock:mybatis:ru.ibank.UserMapper.update")
    MockEndpoint mockMybatisUpdateUser;

    @EndpointInject(uri="mock:mybatis:ru.ibank.UserMapper.delete")
    MockEndpoint mockMybatisDeleteUser;


    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/beans.xml",
                                                                "META-INF/spring/osgi-beans.xml",
                                                                "META-INF/spring/cxf-beans.xml",
                                                                "META-INF/spring/camel-context.xml"});
    }

    @Override
    public String isMockEndpointsAndSkip() {
        return "mybatis:ru.ibank.UserMapper.*";
    }


    @Override
    public boolean isUseDebugger() {
        return true;
    }

    @Override
    protected void debugBefore(Exchange exchange, Processor processor, ProcessorDefinition<?> definition, String id, String label) {
        log.info("Before: " + definition + " Body: " + exchange.getIn().getBody());
    }

    @BeforeClass
    public static void init() {
        user = new UserDTO();
        user.setLastName("Петров");
        user.setFirstName("Петр");
        user.setMiddleName("Петрович");
    }

    @Test
    public void addUserTest() {

        mockMybatisAddUser.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                user.setId(1L);
                exchange.getOut().setBody(user);
            }
        });

        List<UserDTO> params = new ArrayList();
        params.add(user);

        List<Long> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "addUser");

        Long id = result.get(0);
        Assert.assertNotNull(id);

        user.setId(id);
        System.out.println("Создан пользователь, id=" + id);

    }


    @Test
    public void findUserByIdTest() {

        mockMybatisFindUserById.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                UserDTO user = new UserDTO();
                user.setLastName("Петров");
                user.setFirstName("Петр");
                user.setMiddleName("Петрович");
                exchange.getOut().setBody(user);
            }
        });

        List<Long> params = new ArrayList();
        params.add(user.getId());

        List<UserDTO> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "findUserById");
        UserDTO findUser = result.get(0);
        assertEquals(user, findUser);

    }

    @Test
    public void updateUserTest(){

        mockMybatisUpdateUser.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getOut().setHeader("CamelMyBatisResult", 1);
            }
        });

        List<UserDTO> params = new ArrayList();

        user.setFirstName("Иван");
        params.add(user);

        List<Boolean> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "updateUser");
        Boolean updated = result.get(0);
        Assert.assertTrue(updated);
    }

    @Test
    public void deleteUserTest(){

        mockMybatisDeleteUser.whenAnyExchangeReceived(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getOut().setHeader("CamelMyBatisResult", 1);
            }
        });

        List<Long> params = new ArrayList();
        params.add(user.getId());

        List<Boolean> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "deleteUser");
        Boolean deleted = result.get(0);
        Assert.assertTrue(deleted);
    }
}
