package com.sample.gateway.persistence.domain;

import com.sample.gateway.core.event.ApplicationData;
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
public class Application extends BaseEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_provider_id")
    private ApplicationProvider applicationProvider;

    private Application(RegisterApplicationEvent registerApplication) {
        super("created by the persistence layer");
        ApplicationData data = registerApplication.getData();
        this.adminUri = data.getAdminUri();
        this.applicationName = data.getApplicationName();
        this.appUri = data.getAppUri();
        this.description = data.getDescription();
        this.imageUri = data.getImageUri();
        this.redirectUri = data.getRedirectUri();
        this.admin = data.isAdmin();
        this.bulkExtract = data.isBulkExtract();
        this.approved = data.isApproved();
        this.registeredOn = data.getRegisteredOn();

        // likely that this should be a separate event from application registration, but, oh well.
        this.approvedOn = data.getApprovedOn();
        this.clientId = data.getClientId();
        this.sharedSecret = data.getSharedSecret();
    }

    public Application() {}

    public static Application newInstanceFrom(RegisterApplicationEvent registerApplication) {
        return new Application(registerApplication);
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

    public Boolean isApproved() { return approved; }

    public void isApproved(Boolean approved) { this.approved = approved; }

    public Boolean isAdmin() { return admin; }

    public void setAdmin(Boolean admin) { this.admin = admin; }

    public Boolean isBulkExtract() { return bulkExtract; }

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

    public ApplicationProvider getApplicationProvider() {
        return applicationProvider;
    }

    public void setApplicationProvider(ApplicationProvider applicationProvider) {
        this.applicationProvider = applicationProvider;
    }

    public ApplicationData details() {
        ApplicationData dto = new ApplicationData();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
