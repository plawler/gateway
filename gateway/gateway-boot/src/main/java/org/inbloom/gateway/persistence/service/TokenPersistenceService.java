package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.persistence.domain.TokenEntity;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public interface TokenPersistenceService {
    public TokenEntity generateTokenEntity();
}
