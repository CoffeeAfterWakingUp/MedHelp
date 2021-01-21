package com.epam.tcfp.medHelp.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class UserCommentMedicine {
    private Long id;
    private Medicine medicine;
    private User user;
    private String info;
    private java.sql.Timestamp date;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        UserCommentMedicine that = (UserCommentMedicine) o;
        return id.equals(that.id) &&
                medicine.equals(that.medicine) &&
                user.equals(that.user) &&
                info.equals(that.info) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, user, info, date);
    }

    @Override
    public String toString() {
        return "UserCommentMedicine{" +
                "id=" + id +
                ", medicine=" + medicine +
                ", user=" + user +
                ", info='" + info + '\'' +
                ", date=" + date +
                '}';
    }
}
