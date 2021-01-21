package com.epam.tcfp.medHelp.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class DoctorCommentMedicine {
    private Long id;
    private Doctor doctor;
    private Medicine medicine;
    private String pros;
    private String cons;
    private String info;
    private Integer efficiency_rating;
    private Integer priceAndQuality_rating;
    private Integer sideEffects_rating;
    private java.sql.Timestamp date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getEfficiency_rating() {
        return efficiency_rating;
    }

    public void setEfficiency_rating(Integer efficiency_rating) {
        this.efficiency_rating = efficiency_rating;
    }

    public Integer getPriceAndQuality_rating() {
        return priceAndQuality_rating;
    }

    public void setPriceAndQuality_rating(Integer priceAndQuality_rating) {
        this.priceAndQuality_rating = priceAndQuality_rating;
    }

    public Integer getSideEffects_rating() {
        return sideEffects_rating;
    }

    public void setSideEffects_rating(Integer sideEffects_rating) {
        this.sideEffects_rating = sideEffects_rating;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorCommentMedicine that = (DoctorCommentMedicine) o;
        return id.equals(that.id) &&
                doctor.equals(that.doctor) &&
                medicine.equals(that.medicine) &&
                pros.equals(that.pros) &&
                cons.equals(that.cons) &&
                info.equals(that.info) &&
                efficiency_rating.equals(that.efficiency_rating) &&
                priceAndQuality_rating.equals(that.priceAndQuality_rating) &&
                sideEffects_rating.equals(that.sideEffects_rating) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, medicine, pros, cons, info, efficiency_rating, priceAndQuality_rating, sideEffects_rating, date);
    }

    @Override
    public String toString() {
        return "DoctorCommentMedicine{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", medicine=" + medicine +
                ", pros='" + pros + '\'' +
                ", cons='" + cons + '\'' +
                ", info='" + info + '\'' +
                ", efficiency_rating=" + efficiency_rating +
                ", priceAndQuality_rating=" + priceAndQuality_rating +
                ", sideEffects_rating=" + sideEffects_rating +
                ", date=" + date +
                '}';
    }
}
