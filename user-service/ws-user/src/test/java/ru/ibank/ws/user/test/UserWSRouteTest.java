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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserWSRouteTest extends CamelSpringTestSupport {

    private static final String ENDPOINT = "cxf:bean:userWS";
    private static final String OPERATION_NAME = "operationName";
    private static UserDTO user;

    @EndpointInject(uri = "mock:mybatis:ru.ibank.UserMapper.insert")
    MockEndpoint mockMybatisAddUser;

    @EndpointInject(uri = "mock:mybatis:ru.ibank.UserMapper.findUserById")
    MockEndpoint mockMybatisFindUserById;

    @EndpointInject(uri = "mock:mybatis:ru.ibank.UserMapper.update")
    MockEndpoint mockMybatisUpdateUser;

    @EndpointInject(uri = "mock:mybatis:ru.ibank.UserMapper.delete")
    MockEndpoint mockMybatisDeleteUser;


    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/beans.xml",
                "META-INF/spring/osgi-beans.xml",
                "META-INF/spring/cxf-beans.xml",
                "META-INF/spring/camel-context.xml"});
    }

    @Override
    public boolean isCreateCamelContextPerClass() {
        return true;
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
        user.setBirthday(LocalDate.parse("1983-07-11"));
    }

    @Test
    public void addUserTest() {
        mockMybatisAddUser.whenAnyExchangeReceived(exchange -> {
            user.setId(1L);
            exchange.getOut().setBody(user);
        });

        List<UserDTO> params = new ArrayList();
        params.add(user);

        List<Long> result = template.requestBodyAndHeader(ENDPOINT, params, OPERATION_NAME, "addUser", ArrayList.class);

        Long id = result.get(0);
        Assert.assertNotNull("Ошибка создания пользователя", id);
        user.setId(id);
    }


    @Test
    public void findUserByIdTest() {
        mockMybatisFindUserById.whenAnyExchangeReceived(exchange ->
                exchange.getOut().setBody(user));

        List<Long> params = new ArrayList();
        params.add(user.getId());

        List<UserDTO> result = template.requestBodyAndHeader(ENDPOINT, params, OPERATION_NAME, "findUserById", ArrayList.class);
        UserDTO findUser = result.get(0);
        assertNotNull("Пользователь не найден", findUser);
    }

    @Test
    public void updateUserTest() {
        mockMybatisUpdateUser.whenAnyExchangeReceived(exchange ->
                exchange.getOut().setHeader("CamelMyBatisResult", 1));

        List<UserDTO> params = new ArrayList();

        user.setFirstName("Иван");
        params.add(user);

        List<Boolean> result = template.requestBodyAndHeader(ENDPOINT, params, OPERATION_NAME, "updateUser", ArrayList.class);
        Boolean updated = result.get(0);
        Assert.assertTrue("Не удалось обновить пользователя", updated);
    }

    @Test
    public void deleteUserTest() {
        mockMybatisDeleteUser.whenAnyExchangeReceived(exchange ->
                exchange.getOut().setHeader("CamelMyBatisResult", 1));

        List<Long> params = new ArrayList();
        params.add(user.getId());

        List<Boolean> result = template.requestBodyAndHeader(ENDPOINT, params, OPERATION_NAME, "deleteUser", ArrayList.class);
        Boolean deleted = result.get(0);
        Assert.assertTrue("Не удалось удалить пользователя", deleted);
    }
}
