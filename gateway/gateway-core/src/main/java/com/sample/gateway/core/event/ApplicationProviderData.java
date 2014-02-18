package com.sample.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class ApplicationProviderData {

    private Long applicationProviderId;
    private String username;
    private String applicationProviderName;
    private String organizationName;
    private Boolean termsAccepted;
    private Boolean accountConfirmed;

    public Long getApplicationProviderId() {
        return applicationProviderId;
    }

    public void setApplicationProviderId(Long applicationProviderId) {
        this.applicationProviderId = applicationProviderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(Boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public Boolean getAccountConfirmed() {
        return accountConfirmed;
    }

    public void setAccountConfirmed(Boolean accountConfirmed) {
        this.accountConfirmed = accountConfirmed;
    }
}
