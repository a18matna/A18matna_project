package com.example.a18matna_project;

public class kopp {
    private String name;
    private String Category;
    private String Color;
    private int size;
    private int price;

    public kopp(String mName, String mCategory,String mcolor, int msize, int mprice){
        name=mName;
        Category=mCategory;
        Color=mcolor;
        size=msize;
        price=mprice;
    }

    public kopp(String mName){
        name=mName;
        Category="";
        Color="";
        size=-1;
        price=-1;
    }



    public String getColor() {
        String C = "Color: "+Color;
        return C;
    }
    public void setColor(String F) {
        this.Color = F;
    }

    public void setCategory(String C) {
        this.Category = C;
    }
    public String getCategory() {
        String C = "Category: "+Category;
        return C;
    }

    public void setSize(int h) {
        this.size = h;
    }

    public String getSize() {
        String Storlek = "Capacity: "+String.valueOf(size)+"ml";
        return Storlek;
    }
    public void setPrice(int P) {
        this.price = P;
    }

    public String getPrice() {
        String Pris = "Price: "+String.valueOf(price)+"Kr";
        return Pris;
    }

    public String getName() {
        String Namn = "Name: "+String.valueOf(name);
        return Namn;
    }
    public String info(){
        String info = new String();
        info+= ""+name+" is part of the "+Category+" Collection its Color is "+Color+""+size+ ""+price+"";
        return info;
    }

    @Override
    public String toString() {
        return name;
    }

}
