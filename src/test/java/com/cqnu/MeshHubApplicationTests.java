package com.cqnu;

import com.cqnu.bean.User;
import com.cqnu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeshHubApplicationTests {

    @Autowired
    private UserService service;
    @Test
    void contextLoads() {
        User user =new User();
        user.setUsername("xm");
        user.setAge(12);
        service.save(user);
        System.out.println(user.getId());
    }

}
