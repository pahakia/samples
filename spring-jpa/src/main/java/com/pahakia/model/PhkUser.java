package com.pahakia.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "PhkUser")
@Table(name = "Phk_User")
public class PhkUser {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1001)
    private long userId;

    // https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false, nullable = false)
    private Date createdDate = new Date();

    private String nickname; // optional
    private String lastName; // optional
    private String firstName; // optional
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;
    private String authDomain;
    @Column(unique = true)
    private String federatedIdentity;
    @Column(length = 10)
    private String status = "active"; // active, suspended, banned

    @Temporal(TemporalType.TIMESTAMP)
    private Date banTill;

    public Date getBanTill() {
        return banTill;
    }

    public void setBanTill(Date banTill) {
        this.banTill = banTill;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthDomain(String authDomain) {
        this.authDomain = authDomain;
    }

    public void setFederatedIdentity(String federatedIdentity) {
        this.federatedIdentity = federatedIdentity;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthDomain() {
        return authDomain;
    }

    public String getFederatedIdentity() {
        return federatedIdentity;
    }

    public String toString() {
        return "user id=" + userId + ", email=" + email + ", fed id=" + federatedIdentity;
    }
}
