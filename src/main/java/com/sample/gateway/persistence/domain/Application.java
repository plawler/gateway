package com.sample.gateway.persistence.domain;

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
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "shared_secret")
    private String sharedSecret;

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "description")
    private String description;

    @Column(name = "app_uri")
    private String appUri;

    @Column(name = "redirect_uri")
    private String redirectUri;

    @Column(name = "admin_uri")
    private String adminUri;

    @Column(name = "image_uri")
    private String imageUri;

    @Column(name = "is_approved")
    private Byte isApproved;

    @Column(name = "is_admin")
    private Byte isAdmin;

    @Column(name = "is_bulk_extract")
    private Byte isBulkExtract;

    @Column(name = "registered_on")
    private Date registrationDt;

    @Column(name = "approved_on")
    private Date approvalDt;

    @Column(name = "created_at")
    private Date createDt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Date updateDt;

    @Column(name = "updated_by")
    private String updatedBy;

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

    public Date getRegistrationDt() {
        return registrationDt;
    }

    public void setRegistrationDt(Date registrationDt) {
        this.registrationDt = registrationDt;
    }

    public Date getApprovalDt() {
        return approvalDt;
    }

    public void setApprovalDt(Date approvalDt) {
        this.approvalDt = approvalDt;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

//    public ApplicationProvidersEntity getApplicationProvidersByApplicationProviderId() {
//        return applicationProvidersByApplicationProviderId;
//    }
//
//    public void setApplicationProvidersByApplicationProviderId(ApplicationProvidersEntity applicationProvidersByApplicationProviderId) {
//        this.applicationProvidersByApplicationProviderId = applicationProvidersByApplicationProviderId;
//    }

}
