package com.una.project1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "coveage")
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
    @ManyToMany
    @JoinTable(name = "coverage_coveragecategory",
            joinColumns = @JoinColumn(name = "coverage_id"),
            inverseJoinColumns = @JoinColumn(name = "coveragecategory_id"))
    private Set<CoverageCategory> categories = new HashSet<>();


    public Coverage(long id, String name, String description, Long minimumPrice, Long valuationPercentagePrice, Set<CoverageCategory> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minimumPrice = minimumPrice;
        this.valuationPercentagePrice = valuationPercentagePrice;
        this.categories = categories;
    }


    public Coverage() {

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

    public Set<CoverageCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<CoverageCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coverage coverage)) return false;
        return getId() == coverage.getId() && Objects.equals(getName(), coverage.getName()) && Objects.equals(getDescription(), coverage.getDescription()) && Objects.equals(getMinimumPrice(), coverage.getMinimumPrice()) && Objects.equals(getValuationPercentagePrice(), coverage.getValuationPercentagePrice()) && Objects.equals(getCategories(), coverage.getCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getMinimumPrice(), getValuationPercentagePrice(), getCategories());
    }

    @Override
    public String toString() {
        return "Coverage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minimumPrice=" + minimumPrice +
                ", valuationPercentagePrice=" + valuationPercentagePrice +
                ", categories=" + categories +
                '}';
    }





}
