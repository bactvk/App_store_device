package com.example.storedeviceonline.model;

public class SanPham {
    public int Id;
    public String nameSP;
    public Integer priceSP;
    public String imageSP;
    public String descriptSP;
    public int category_id;

    public SanPham(int id, String nameSP, Integer priceSP, String imageSP, String descriptSP, int category_id) {
        Id = id;
        this.nameSP = nameSP;
        this.priceSP = priceSP;
        this.imageSP = imageSP;
        this.descriptSP = descriptSP;
        this.category_id = category_id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameSP() {
        return nameSP;
    }

    public void setNameSP(String nameSP) {
        this.nameSP = nameSP;
    }

    public Integer getPriceSP() {
        return priceSP;
    }

    public void setPriceSP(Integer priceSP) {
        this.priceSP = priceSP;
    }

    public String getImageSP() {
        return imageSP;
    }

    public void setImageSP(String imageSP) {
        this.imageSP = imageSP;
    }

    public String getDescriptSP() {
        return descriptSP;
    }

    public void setDescriptSP(String descriptSP) {
        this.descriptSP = descriptSP;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
