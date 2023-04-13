package com.una.project1.model;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String expirationDate;
    @Column(nullable = false)
    private String securityCode;
    @Column(nullable = false)
    private String billingAddress;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable=false, updatable=false)
    private User user;

    public Payment() {

    }
    public Payment(String number, String name, String expirationDate, String securityCode, String billingAddress, User user) {
        this.number = number;
        this.name = name;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.billingAddress = billingAddress;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}
