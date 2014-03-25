package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.persistence.domain.TokenEntity;
import org.inbloom.gateway.persistence.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
@Service
public class TokenPersistenceHandler implements TokenPersistenceService{

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenEntity generateTokenEntity() {
        TokenEntity tokenEntity = new TokenEntity();
        //TODO generate real token
        tokenEntity.setToken(new Date().toString());
        tokenRepository.save(tokenEntity);
        return tokenEntity;
    }
}
