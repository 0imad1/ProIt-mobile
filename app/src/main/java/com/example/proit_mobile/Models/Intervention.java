package com.example.proit_mobile.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Intervention {

    @SerializedName("id")
    private Long id;

    @SerializedName("date")
    private String date; // Use String to handle date in "yyyy-MM-dd" format

    @SerializedName("heureDebut")
    private String heureDebut; // Use String to handle time in "HH:mm:ss" format

    @SerializedName("heureFin")
    private String heureFin; // Use String to handle time in "HH:mm:ss" format

    @SerializedName("status")
    private String status;

    @SerializedName("compagnie")
    private Compagnie compagnie;

    @SerializedName("appUser")
    private AppUser appUser;

    @SerializedName("comptoire")
    private Comptoire comptoire;

    @SerializedName("equipment")
    private Equipment equipment;

    @SerializedName("solution")
    private Solution solution;

    @SerializedName("probleme")
    private Probleme probleme;

    @SerializedName("aeroport")
    private Aeroport aeroport;

    @SerializedName("projet")
    private Projet projet;

    // Getters and setters
    public Intervention(Long id, String date, String heureDebut, String heureFin, String status, Compagnie compagnie, AppUser appUser, Comptoire comptoire, Equipment equipment, Solution solution, Probleme probleme, Aeroport aeroport, Projet projet) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.status = status;
        this.compagnie = compagnie;
        this.appUser = appUser;
        this.comptoire = comptoire;
        this.equipment = equipment;
        this.solution = solution;
        this.probleme = probleme;
        this.aeroport = aeroport;
        this.projet = projet;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Comptoire getComptoire() {
        return comptoire;
    }

    public void setComptoire(Comptoire comptoire) {
        this.comptoire = comptoire;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Probleme getProbleme() {
        return probleme;
    }

    public void setProbleme(Probleme probleme) {
        this.probleme = probleme;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
