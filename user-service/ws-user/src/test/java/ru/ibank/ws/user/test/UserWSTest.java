package ru.ibank.ws.user.test;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.cxf.binding.soap.SoapFault;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ibank.db.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;


public class UserWSTest extends CamelSpringTestSupport {

    private final String ENDPOINT = "cxf:bean:userWS";
    private static UserDTO user;

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/beans.xml",
                                                                "META-INF/spring/osgi-beans.xml",
                                                                "META-INF/spring/cxf-beans.xml",
                                                                "META-INF/spring/camel-context.xml"});
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
        List<Long> params = new ArrayList();
        params.add(user.getId());

        List<UserDTO> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "findUserById");
        UserDTO findUser = result.get(0);
        assertEquals(user, findUser);
    }

    @Test
    public void updateUserTest() {
        List<UserDTO> params = new ArrayList();
        user.setFirstName("Иван");
        params.add(user);

        List<Boolean> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "updateUser");
        Boolean updated = result.get(0);
        Assert.assertTrue(updated);
    }

    @Test
    public void deleteUser() {
        List<Long> params = new ArrayList();
        params.add(user.getId());

        List<Boolean> result = (ArrayList) template.requestBodyAndHeader(ENDPOINT, params, "operationName", "deleteUser");
        Boolean deleted = result.get(0);
        Assert.assertTrue(deleted);
    }

    @Test
    public void findNotExistUserByIdTest() {
        List<Long> params = new ArrayList();
        params.add(-1L);
        try {
            template.requestBodyAndHeader(ENDPOINT, params, "operationName", "findUserById");
        } catch (CamelExecutionException e){
           SoapFault fault = e.getExchange().getException(SoapFault.class);
           Assert.assertEquals("User not found", fault.getMessage());
        }
    }

}
