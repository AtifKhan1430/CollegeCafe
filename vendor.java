package com.example.final1;

public class vendor {
    private int vendorid;
    private String vname;
    private String vlocation;
    private String vid;
    private String vemail;
    private String vpassword;
    public vendor(String name, String location, String reg, String email, String password) {
        this.vname = name;
        this.vlocation = location;
        this.vid = reg;
        this.vemail = email;
        this.vpassword = password;
    }

    public int getId() {
        return vendorid;
    }

    public void setId(int id) {
        this.vendorid = id;
    }

    public String getName() {
        return vname;
    }

    public void setName(String name) {
        this.vname = name;
    }

    public String getLocation() {
        return vlocation;
    }

    public void setLocation(String location) {
        this.vlocation = location;
    }

    public String getReg() {
        return vid;
    }

    public void setReg(String reg) {
        this.vid = reg;
    }

    public String getEmail() {
        return vemail;
    }

    public void setEmail(String email) {
        this.vemail = email;
    }
    public String getPassword() {
        return vpassword;
    }
    public void setStaus(String password) {
        this.vpassword = password;
    }

}
