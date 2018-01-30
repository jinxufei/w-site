package com.urwoo.dao;

import com.urwoo.enums.Gender;
import com.urwoo.enums.Level;
import com.urwoo.enums.LoginType;
import com.urwoo.enums.Status;
import com.urwoo.mapper.UserMapper;
import com.urwoo.po.UserPo;
import com.urwoo.user.UserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
public class UserDaoTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void save(){
        UserPo u = new UserPo();
        u.setUsername("zs");
        u.setNickname("zsybd");
        u.setPassword("123456");
        u.setPhone("13212345678");
        u.setEmail("123@sina.com");
        u.setStatus(Status.OFF.code());
        u.setLoginType(LoginType.SELF.code());
        u.setGender(Gender.MALE.code());
        u.setMeta("111");
        u.setLevel(Level.LEVEL_1.code());
        u.setAvatar("111");
        u.setMd5("111");
        userMapper.save(u);
    }
}
