package com.epam.tcfp.medHelp.entity;


import java.util.Objects;

public class MedicineByPharmacy {
    private Long id;
    private Medicine medicine;
    private Pharmacy pharmacy;
    private Integer price;
    private Boolean exist;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
        MedicineByPharmacy that = (MedicineByPharmacy) o;
        return id.equals(that.id) &&
                medicine.equals(that.medicine) &&
                pharmacy.equals(that.pharmacy) &&
                price.equals(that.price) &&
                exist.equals(that.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, pharmacy, price, exist);
    }

    @Override
    public String toString() {
        return "MedicineByPharmacy{" +
                "id=" + id +
                ", medicine=" + medicine +
                ", pharmacy=" + pharmacy +
                ", price=" + price +
                ", exist=" + exist +
                '}';
    }
}
