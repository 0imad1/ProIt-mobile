package com.example.proit_mobile.Models;

public class Aeroport {
    private Long id;
    private String aeroportName;

    public Aeroport(String string) {
        this.aeroportName=string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAeroportName() {
        return aeroportName;
    }

    public void setAeroportName(String aeroportName) {
        this.aeroportName = aeroportName;
    }
}
