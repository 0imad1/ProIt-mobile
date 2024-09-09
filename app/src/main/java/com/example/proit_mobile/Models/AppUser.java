package com.example.proit_mobile.Models;

public class AppUser {
    private Long id ;
    private String username;


    public AppUser(String username) {
        this.username=username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
