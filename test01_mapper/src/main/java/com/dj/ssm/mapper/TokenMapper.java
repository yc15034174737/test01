package com.dj.ssm.mapper;

import com.dj.ssm.pojo.Token;
import org.springframework.dao.DataAccessException;

public interface TokenMapper {

    Token findTokenByUserId(Integer userId) throws DataAccessException;

    void updateToken(Token token) throws DataAccessException;

    void addToken(Token token) throws DataAccessException;

    Token findTokenByUserToken(String token) throws DataAccessException;

}
