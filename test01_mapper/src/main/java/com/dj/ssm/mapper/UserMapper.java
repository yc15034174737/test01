package com.dj.ssm.mapper;

import com.dj.ssm.pojo.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapper {

    User findUserByUserNameAndUserPwd(User user) throws DataAccessException;

    List<User> findAll(Integer isDel) throws DataAccessException;

    User findUserById(Integer id) throws DataAccessException;

    void addUser(User user) throws DataAccessException;

    void updateUser(User user) throws DataAccessException;

    User findUserByUserName(String userName) throws DataAccessException;
}
