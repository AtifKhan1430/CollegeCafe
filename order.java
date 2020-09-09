package com.example.final1;

public class order {
    private int id;
    private String name;
    private String price;
    private String quantity;
    private String total;
    private String status;
    private String email;
    public order(String name, String price, String quantity, String total, String status,String email, int id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    public String getStaus() {
        return status;
    }
    public void setStaus(String status) {
        this.status = status;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String quantity) {
        this.email = email;
    }

}
