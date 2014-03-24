package org.inbloom.gateway.persistence.domain;

import javax.persistence.*;

/**
 * Created by lloydengebretsen on 3/20/14.
 */
@Entity(name="application_providers")
public class ApplicationProviderEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationProviderId;

    private String applicationProviderName;
    private String organizationName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Long getApplicationProviderId() {
        return applicationProviderId;
    }

    public void setApplicationProviderId(Long applicationProviderId) {
        this.applicationProviderId = applicationProviderId;
    }

    public String getApplicationProviderName() {
        return applicationProviderName;
    }

    public void setApplicationProviderName(String applicationProviderName) {
        this.applicationProviderName = applicationProviderName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
