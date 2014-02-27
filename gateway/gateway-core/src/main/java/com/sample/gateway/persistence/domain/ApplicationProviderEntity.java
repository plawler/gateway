package com.sample.gateway.persistence.domain;

import com.sample.gateway.core.event.ApplicationProviderData;
import com.sample.gateway.core.event.RegisterApplicationProviderEvent;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;


/**
 * Created by lloydengebretsen on 2/17/14.
 */
@Entity(name = "application_providers")
public class ApplicationProviderEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationProviderId;

    private String username;
    private String applicationProviderName;
    private String organizationName;
    @Column(name="is_terms_accepted", columnDefinition = "TINYINT(1)")
    private Boolean termsAccepted;
    @Column(name="is_account_confirmed", columnDefinition = "TINYINT(1)")
    private Boolean accountConfirmed;

    public ApplicationProviderEntity(String createdBy){
        super(createdBy);
    }

    public ApplicationProviderEntity(){
        super();
    }


    public static ApplicationProviderEntity instanceFrom(RegisterApplicationProviderEvent registerApplicationProviderEvent) {
        ApplicationProviderEntity applicationProviderEntity = new ApplicationProviderEntity();
        BeanUtils.copyProperties(registerApplicationProviderEvent.getData(), applicationProviderEntity);
        return applicationProviderEntity;
    }

    public ApplicationProviderData details() {
        ApplicationProviderData dto = new ApplicationProviderData();
        BeanUtils.copyProperties(this, dto);
        return dto;
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

    public Long getApplicationProviderId() {

        return applicationProviderId;
    }

    public void setApplicationProviderId(Long applicationProviderId) {
        this.applicationProviderId = applicationProviderId;
    }



//    public Collection<ApplicationEntity> getApplications() {
//        return applications;
//    }
//
//    public void setApplications(Collection<ApplicationEntity> applications) {
//        this.applications = applications;
//    }

}
