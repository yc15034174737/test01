package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.TokenMapper;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;
    @Override
    public Token findTokenByUserId(Integer userId) throws Exception {
        return tokenMapper.findTokenByUserId(userId);
    }

    @Override
    public void updateToken(Token token) throws Exception {
        tokenMapper.updateToken(token);
    }

    @Override
    public void addToken(Token token) throws Exception {
        tokenMapper.addToken(token);
    }

    @Override
    public Token findTokenByUserToken(String token) throws Exception {
        return tokenMapper.findTokenByUserToken(token);
    }
}
