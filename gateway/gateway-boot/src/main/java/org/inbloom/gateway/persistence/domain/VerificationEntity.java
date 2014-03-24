package org.inbloom.gateway.persistence.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lloydengebretsen on 3/20/14.
 */
@Entity(name="verifications")
public class VerificationEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationId;

    @Column(name="is_verified", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean verified;
    private Date validFrom;
    private Date validUntil;

    private String clientIpAddress;

    @ManyToOne
    @JoinColumn(name="token_id")
    private TokenEntity token;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public TokenEntity getToken() {
        return token;
    }

    public void setToken(TokenEntity token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
