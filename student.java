package com.example.final1;

public class student {  private int vendorid;
    private int id;
    private String name;
    private String rollno;
    private String email;
    private String password;
    public student(String name, String rollno, String email, String password) {
        this.name = name;
        this.rollno = rollno;
        this.email = email;
        this.password = password;
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

    public String getReg() {
        return rollno;
    }

    public void setReg(String reg) {
        this.rollno = rollno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setStaus(String password) {
        this.password = password;
    }

}

