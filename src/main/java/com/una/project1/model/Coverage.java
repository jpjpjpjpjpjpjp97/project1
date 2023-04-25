package com.una.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "coverage")
public class Coverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank(message = "Name cannot be empty.")
    String name;
    @NotBlank(message = "Name cannot be empty.")
    String description;
    @NotBlank(message = "Name cannot be empty.")
    Long minimumPrice;

    Long valuationPercentagePrice;

    /*
    @ManyToMany
    @JoinTable(name = "coverage_coverageCategory",
            joinColumns = @JoinColumn(name = "coverage_id"),
            inverseJoinColumns = @JoinColumn(name = "coveragecategory_id"))
    private Set<CoverageCategory> categories = new HashSet<>();
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coverageCategory_id", referencedColumnName = "id")
    private CoverageCategory coverageCategory;




    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "coverages")
    private Set<Insurance> insurances = new HashSet<>();

    public Coverage() {
    }

    public Coverage(long id, String name, String description, Long minimumPrice, Long valuationPercentagePrice, CoverageCategory coverageCategory, Set<Insurance> insurances) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minimumPrice = minimumPrice;
        this.valuationPercentagePrice = valuationPercentagePrice;
        this.coverageCategory = coverageCategory;
        this.insurances = insurances;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Long minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Long getValuationPercentagePrice() {
        return valuationPercentagePrice;
    }

    public void setValuationPercentagePrice(Long valuationPercentagePrice) {
        this.valuationPercentagePrice = valuationPercentagePrice;
    }

    public CoverageCategory getCoverageCategory() {
        return coverageCategory;
    }

    public void setCoverageCategory(CoverageCategory coverageCategory) {
        this.coverageCategory = coverageCategory;
    }

    public Set<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(Set<Insurance> insurances) {
        this.insurances = insurances;
    }









}
