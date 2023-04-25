package com.una.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "numberPlate is required.")
    @Column()
    private String numberPlate;

    @NotBlank(message = "A year is required.")
    @Column()
    private int year;

    @NotBlank(message = "A valuation is required.")
    @Column()
    private int valuation;

    @NotBlank(message = "A date is required.")
    @Column()
    private Date startDate;
    //Puede ser la clase que llama a date

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paymentSchedule_id", referencedColumnName = "id")
    private PaymentSchedule paymentSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "insurance_coverage",
            joinColumns = @JoinColumn(name = "insurance_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coverage_id", referencedColumnName = "id"))
    private Set<Coverage> coverages = new HashSet<>();

    public Insurance() {
    }

    public Insurance(Long id, String numberPlate, int year, int valuation, Date startDate, Payment payment, PaymentSchedule paymentSchedule, User client, Vehicle vehicle, Set<Coverage> coverages) {
        this.id = id;
        this.numberPlate = numberPlate;
        this.year = year;
        this.valuation = valuation;
        this.startDate = startDate;
        this.payment = payment;
        this.paymentSchedule = paymentSchedule;
        this.client = client;
        this.vehicle = vehicle;
        this.coverages = coverages;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValuation() {
        return valuation;
    }

    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Set<Coverage> getCoverages() {
        return coverages;
    }

    public void setCoverages(Set<Coverage> coverages) {
        this.coverages = coverages;
    }
}
