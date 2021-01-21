package com.epam.tcfp.medHelp.entity;

import java.util.Objects;

public class Pharmacy {
    private Long id;
    private String name;
    private City city;
    private String address;
    private String phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        Pharmacy pharmacy = (Pharmacy) o;
        return id.equals(pharmacy.id) &&
                name.equals(pharmacy.name) &&
                city.equals(pharmacy.city) &&
                address.equals(pharmacy.address) &&
                phone.equals(pharmacy.phone) &&
                approved.equals(pharmacy.approved) &&
                exist.equals(pharmacy.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, address, phone, approved, exist);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", approved=" + approved +
                ", exist=" + exist +
                '}';
    }
}
