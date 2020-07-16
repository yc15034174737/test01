package com.dj.ssm.service;

import com.dj.ssm.pojo.Token;

public interface TokenService {

    Token findTokenByUserId(Integer userId) throws Exception;

    void updateToken(Token token) throws Exception;

    void addToken(Token token) throws Exception;

    Token findTokenByUserToken(String token) throws Exception;
}
