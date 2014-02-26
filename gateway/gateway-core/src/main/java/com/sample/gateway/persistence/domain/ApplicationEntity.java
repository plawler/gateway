package com.sample.gateway.persistence.domain;

import com.sample.gateway.core.event.RegisterApplicationEvent;
import org.springframework.beans.BeanUtils;

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
public class ApplicationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private String clientId;
    private String sharedSecret;
    private String applicationName;
    private String description;
    private String appUri;
    private String redirectUri;
    private String adminUri;
    private String imageUri;

    @Column(name="is_approved", columnDefinition = "TINYINT(1)")
    private Boolean approved;

    @Column(name="is_admin", columnDefinition = "TINYINT(1)")
    private Boolean admin;

    @Column(name="is_bulk_extract", columnDefinition = "TINYINT(1)")
    private Boolean bulkExtract;

    private Date registeredOn;
    private Date approvedOn;

    @ManyToOne
    @JoinColumn(name = "application_provider_id")
    private ApplicationProviderEntity applicationProviderEntity;

    public ApplicationEntity() {
        super("created by the persistence layer");
    }

    public static ApplicationEntity newInstanceFrom(RegisterApplicationEvent registerApplication) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        BeanUtils.copyProperties(registerApplication.getData(), applicationEntity);
        return applicationEntity;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) { this.admin = admin; }

    public Boolean getBulkExtract() {
        return bulkExtract;
    }

    public void setBulkExtract(Boolean bulkExtract) {
        this.bulkExtract = bulkExtract;
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

    public ApplicationProviderEntity getApplicationProviderEntity() {
        return applicationProviderEntity;
    }

    public void setApplicationProviderEntity(ApplicationProviderEntity applicationProviderEntity) {
        this.applicationProviderEntity = applicationProviderEntity;
    }

}
