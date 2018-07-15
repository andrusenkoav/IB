package ru.ibank.ws.user;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.ibank.db.user.User;
import ru.ibank.db.user.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class TestProcess implements Processor {

    private UserMapper userMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        List params = (ArrayList) exchange.getIn().getBody(ArrayList.class);

        Long id = (Long) params.get(0);
        User user = userMapper.findUserById(id);
        exchange.getOut().setBody(user);

    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
