package com.epam.tcfp.medHelp.entity;

import java.util.Objects;

public class MedCenter {
    private Long id;
    private String name;
    private City city;
    private String phone;
    private String address;
    private String medInstitution;
    private Boolean approved;
    private Boolean exist;


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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedInstitution() {
        return medInstitution;
    }

    public void setMedInstitution(String medInstitution) {
        this.medInstitution = medInstitution;
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
        MedCenter medCenter = (MedCenter) o;
        return id.equals(medCenter.id) &&
                name.equals(medCenter.name) &&
                city.equals(medCenter.city) &&
                phone.equals(medCenter.phone) &&
                address.equals(medCenter.address) &&
                medInstitution.equals(medCenter.medInstitution) &&
                approved.equals(medCenter.approved) &&
                exist.equals(medCenter.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, phone, address, medInstitution, approved, exist);
    }

    @Override
    public String toString() {
        return "MedCenter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", medInstitution='" + medInstitution + '\'' +
                ", approved=" + approved +
                ", exist=" + exist +
                '}';
    }
}
