package com.example.proit_mobile.Models;

import com.google.gson.annotations.SerializedName;

public class Equipment {
    private Long id;
    @SerializedName("equipmentName")

    private String equipementName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipementName() {
        return equipementName;
    }

    public void setEquipementName(String equipementName) {
        this.equipementName = equipementName;
    }
}
