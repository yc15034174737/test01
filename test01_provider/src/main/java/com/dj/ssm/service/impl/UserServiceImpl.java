package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserNameAndUserPwd(User user) throws Exception {
        return userMapper.findUserByUserNameAndUserPwd(user);
    }

    @Override
    public List<User> findAll(Integer isDel) throws Exception {
        return userMapper.findAll(isDel);
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        return userMapper.findUserById(id);
    }

    @Override
    public void addUser(User user) throws Exception {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByUserName(String userName) throws Exception {
        return userMapper.findUserByUserName(userName);
    }
}
