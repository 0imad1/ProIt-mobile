package com.example.proit_mobile.Models;

public class Compagnie {
    private Long id;
    private String compagnieName;

    public Compagnie(String string) {
        this.compagnieName=string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompagnieName() {
        return compagnieName;
    }

    public void setCompagnieName(String compagnieName) {
        this.compagnieName = compagnieName;
    }
}
