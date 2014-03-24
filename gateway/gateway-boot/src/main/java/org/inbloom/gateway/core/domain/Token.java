package org.inbloom.gateway.core.domain;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class Token {

    private Long tokenId;

    private String token;

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
