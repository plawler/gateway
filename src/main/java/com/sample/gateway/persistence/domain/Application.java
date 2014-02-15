package com.sample.gateway.persistence.domain;

import com.sample.gateway.persistence.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/14/14
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "applications")
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    private String clientId;
    private String sharedSecret;
    private String applicationName;
    private String description;
    private String appUri;
    private String redirectUri;
    private String adminUri;
    private String imageUri;
    private Byte isApproved;
    private Byte isAdmin;
    private Byte isBulkExtract;
    private Date registeredOn;
    private Date approvedOn;
//    @ManyToOne
//    @JoinColumn(name = "application_provider_id", referencedColumnName = "application_provider_id", nullable = false)
//    private ApplicationProvidersEntity applicationProvidersByApplicationProviderId;


    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppUri() {
        return appUri;
    }

    public void setAppUri(String appUri) {
        this.appUri = appUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getAdminUri() {
        return adminUri;
    }

    public void setAdminUri(String adminUri) {
        this.adminUri = adminUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Byte getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Byte isApproved) {
        this.isApproved = isApproved;
    }

    public Byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Byte getIsBulkExtract() {
        return isBulkExtract;
    }

    public void setIsBulkExtract(Byte isBulkExtract) {
        this.isBulkExtract = isBulkExtract;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

//    public ApplicationProvidersEntity getApplicationProvidersByApplicationProviderId() {
//        return applicationProvidersByApplicationProviderId;
//    }
//
//    public void setApplicationProvidersByApplicationProviderId(ApplicationProvidersEntity applicationProvidersByApplicationProviderId) {
//        this.applicationProvidersByApplicationProviderId = applicationProvidersByApplicationProviderId;
//    }

}
