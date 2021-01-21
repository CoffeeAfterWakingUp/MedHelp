package com.epam.tcfp.medHelp.entity;

import java.util.Objects;

public class Medicine {
    private Long id;
    private String name;
    private String manufacturer;
    private MedicineFor medicineFor;
    private MedicineFrom medicineFrom;
    private MedicineHow medicineHow;
    private String shortDescription;
    private String pharmacology;
    private String dosage;
    private String sideEffect;
    private String indication;
    private String contraindication;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MedicineFor getMedicineFor() {
        return medicineFor;
    }

    public void setMedicineFor(MedicineFor medicineFor) {
        this.medicineFor = medicineFor;
    }

    public MedicineFrom getMedicineFrom() {
        return medicineFrom;
    }

    public void setMedicineFrom(MedicineFrom medicineFrom) {
        this.medicineFrom = medicineFrom;
    }

    public MedicineHow getMedicineHow() {
        return medicineHow;
    }

    public void setMedicineHow(MedicineHow medicineHow) {
        this.medicineHow = medicineHow;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public void setPharmacology(String pharmacology) {
        this.pharmacology = pharmacology;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getContraindication() {
        return contraindication;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
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
        Medicine medicine = (Medicine) o;
        return id.equals(medicine.id) &&
                name.equals(medicine.name) &&
                manufacturer.equals(medicine.manufacturer) &&
                Objects.equals(medicineFor, medicine.medicineFor) &&
                Objects.equals(medicineFrom, medicine.medicineFrom) &&
                Objects.equals(medicineHow, medicine.medicineHow) &&
                shortDescription.equals(medicine.shortDescription) &&
                pharmacology.equals(medicine.pharmacology) &&
                dosage.equals(medicine.dosage) &&
                sideEffect.equals(medicine.sideEffect) &&
                indication.equals(medicine.indication) &&
                contraindication.equals(medicine.contraindication) &&
                exist.equals(medicine.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, medicineFor, medicineFrom, medicineHow, shortDescription, pharmacology, dosage, sideEffect, indication, contraindication, exist);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", medicineFor=" + medicineFor +
                ", medicineFrom=" + medicineFrom +
                ", medicineHow=" + medicineHow +
                ", shortDescription='" + shortDescription + '\'' +
                ", pharmacology='" + pharmacology + '\'' +
                ", dosage='" + dosage + '\'' +
                ", sideEffect='" + sideEffect + '\'' +
                ", indication='" + indication + '\'' +
                ", contraindication='" + contraindication + '\'' +
                ", exist=" + exist +
                '}';
    }
}
