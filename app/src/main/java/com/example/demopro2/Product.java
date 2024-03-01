package com.example.demopro2;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer IdPro;
    private String NamePro;
    private String DecriptionPro;
    private String Price;
    private String ImagePro;

    public Product(Integer idPro, String namePro, String decriptionPro, String price, String imagePro) {
        IdPro = idPro;
        NamePro = namePro;
        DecriptionPro = decriptionPro;
        Price = price;
        ImagePro = imagePro;
    }

    public Integer getIdPro() {
        return IdPro;
    }

    public void setIdPro(Integer idPro) {
        IdPro = idPro;
    }

    public String getNamePro() {
        return NamePro;
    }

    public void setNamePro(String namePro) {
        NamePro = namePro;
    }

    public String getDecriptionPro() {
        return DecriptionPro;
    }

    public void setDecriptionPro(String decriptionPro) {
        DecriptionPro = decriptionPro;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImagePro() {
        return ImagePro;
    }

    public void setImagePro(String imagePro) {
        ImagePro = imagePro;
    }
    public  String toString(){
        return IdPro+"\t"+ NamePro+"\t"+DecriptionPro+"\t"+Price+"\t"+ ImagePro;
    }
}