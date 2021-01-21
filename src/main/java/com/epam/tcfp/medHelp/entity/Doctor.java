package com.epam.tcfp.medHelp.entity;


import java.util.Objects;

public class Doctor {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private MedCenter medCenter;
    private Profession profession;
    private Integer experience;
    private Boolean approved;
    private Boolean exist;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MedCenter getMedCenter() {
        return medCenter;
    }

    public void setMedCenter(MedCenter medCenter) {
        this.medCenter = medCenter;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id) &&
                email.equals(doctor.email) &&
                password.equals(doctor.password) &&
                firstName.equals(doctor.firstName) &&
                lastName.equals(doctor.lastName) &&
                phone.equals(doctor.phone) &&
                medCenter.equals(doctor.medCenter) &&
                profession.equals(doctor.profession) &&
                experience.equals(doctor.experience) &&
                approved.equals(doctor.approved) &&
                exist.equals(doctor.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, firstName, lastName, phone, medCenter, profession, experience, approved, exist);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", medCenter=" + medCenter +
                ", profession=" + profession +
                ", experience=" + experience +
                ", approved=" + approved +
                ", exist=" + exist +
                '}';
    }
}
