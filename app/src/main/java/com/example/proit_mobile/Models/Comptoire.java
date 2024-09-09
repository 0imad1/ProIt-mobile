package com.example.proit_mobile.Models;

public class Comptoire {
    private Long id;
    private String comptoireName;

    public Comptoire(String string) {
        this.comptoireName=string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComptoireName() {
        return comptoireName;
    }

    public void setComptoireName(String comptoireName) {
        this.comptoireName = comptoireName;
    }
}
