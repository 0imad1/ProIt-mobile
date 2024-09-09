package com.example.proit_mobile.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InterventionDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

    private String date;
// Change to Date
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

private String heureDebut; // Change to Date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

    private String heureFin;   // Keep as Date
    private String status;
    private Long compagnie;
    private String appUser;
    private Long comptoire;
    private Long equipment;
    private Long solution;
    private Long probleme;
    private Long aeroport;
    private Long projet;

    public InterventionDto(Long id, String date, String heureDebut, String status, String heureFin, Long compagnie, String appUser, Long comptoire, Long equipment, Long solution, Long probleme, Long aeroport, Long projet) {
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

    public Long getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Long compagnie) {
        this.compagnie = compagnie;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public Long getComptoire() {
        return comptoire;
    }

    public void setComptoire(Long comptoire) {
        this.comptoire = comptoire;
    }

    public Long getEquipment() {
        return equipment;
    }

    public void setEquipment(Long equipment) {
        this.equipment = equipment;
    }

    public Long getSolution() {
        return solution;
    }

    public void setSolution(Long solution) {
        this.solution = solution;
    }

    public Long getProbleme() {
        return probleme;
    }

    public void setProbleme(Long probleme) {
        this.probleme = probleme;
    }

    public Long getAeroport() {
        return aeroport;
    }

    public void setAeroport(Long aeroport) {
        this.aeroport = aeroport;
    }

    public Long getProjet() {
        return projet;
    }

    public void setProjet(Long projet) {
        this.projet = projet;
    }
}
