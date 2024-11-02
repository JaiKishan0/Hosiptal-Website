package com.Hospital.Management.System.dto;

public class DoctorDTO {

    private Long id;
    private String name;
    private String specialization;
    private long contact; // Include contact field

    public DoctorDTO() {
        super();
    }

    public DoctorDTO(Long id, String name, String specialization, long contact) {
        super();
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
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
        return contact; // Getter for contact
    }

    public void setContact(long contact) {
        this.contact = contact; // Setter for contact
    }
}
