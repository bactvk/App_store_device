package com.example.storedeviceonline.model;

public class LoaiSP {
    public int Id;
    public String nameCategory;
    public String imageCategory;

    public LoaiSP(int id, String nameCategory, String imageCategory) {
        Id = id;
        this.nameCategory = nameCategory;
        this.imageCategory = imageCategory;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }
}
