package com.dj.ssm.service;

import com.dj.ssm.pojo.User;

import java.util.List;

public interface UserService {

    User findUserByUserNameAndUserPwd(User user) throws Exception;

    List<User> findAll(Integer isDel) throws Exception;

    User findUserById(Integer id) throws Exception;

    void addUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    User findUserByUserName(String userName) throws Exception;
}
