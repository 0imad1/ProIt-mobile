package com.example.proit_mobile.Models;

public class Projet {
    private Long id ;
    private String projetName;

    public Projet(String string) {
        this.projetName=string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjetName() {
        return projetName;
    }

    public void setProjetName(String projetName) {
        this.projetName = projetName;
    }
}
