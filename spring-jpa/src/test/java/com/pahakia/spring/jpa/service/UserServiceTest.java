package com.pahakia.spring.jpa.service;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pahakia.samples.spring.jpa.config.JpaConfig;
import pahakia.samples.spring.jpa.service.UserService;

import com.pahakia.model.PhkUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void test1() {
        PhkUser user = new PhkUser();
        user.setFederatedIdentity("123abc");
        user.setEmail("hello@world.com");
        user.setRole("User");
        userService.save(user);
        PhkUser user2 = userService.findByFederatedId("123abc");
        assertEquals(user.getUserId(), user2.getUserId());
    }

}
