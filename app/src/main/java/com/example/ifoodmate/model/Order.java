package com.example.ifoodmate.model;

public class Order {
    private int id;
    private String name;
    private String quantity;
    private String price;

    public String getTotalprice() {
        return totalprice;
    }

    private String totalprice;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }
    /*public String getTotalPrice()
    {
        int qty = Integer.parseInt(quantity);
        int pr = Integer.parseInt(price);
        int itemprice = qty * pr;

        return String.valueOf(itemprice);
    }*/

    public void setPrice(String price) {
        this.price = price;
    }

    public Order(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Order()
    {}
}
