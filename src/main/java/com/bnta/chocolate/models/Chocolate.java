package com.bnta.chocolate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chocolates")
public class Chocolate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;
    @Column(name = "cocoa_percentage")
    private int cocoaPercentage;

//    @ManyToOne
//    @JoinColumn(name = "estate_id", nullable = false) // you dont need join for many to many
    @ManyToMany
    @JsonIgnoreProperties({"chocolates"})
    @JoinTable( //the owner of the many to many relationship gets the @jointable
            name = "chocolates_estates", // if you have a good name give it one if not put the two table names table1_table2
            joinColumns = @JoinColumn(name = "chocolate_id"), // we are in this class thats why we make it the join column
            inverseJoinColumns = @JoinColumn(name = "estate_id")// inverse join column is the other table
    )
    private List<Estate> estates; // plural means list most of the time

    public Chocolate(String name, int cocoaPercentage) {
        this.name = name;
        this.cocoaPercentage = cocoaPercentage;
        this.estates = new ArrayList<>();
    }

    public Chocolate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCocoaPercentage() {
        return cocoaPercentage;
    }

    public void setCocoaPercentage(int cocoaPercentage) {
        this.cocoaPercentage = cocoaPercentage;
    }

    public List<Estate> getEstates() {
        return estates;
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }

    public void addEstate(Estate estate){
        this.estates.add(estate); // works with all simplified many to many
    }
}
