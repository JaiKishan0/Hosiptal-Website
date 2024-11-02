package com.Hospital.Management.System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialization;

    private long contact; // Added contact field

    @OneToOne
    private User user;

    public Doctor() {
        super();
    }

    public Doctor(Long id, String name, String specialization, long contact, User user) {
        super();
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.user = user;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public long getContact() {
        return contact; // Added getter for contact
    }

    public void setContact(long contact) {
        this.contact = contact; // Added setter for contact
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
