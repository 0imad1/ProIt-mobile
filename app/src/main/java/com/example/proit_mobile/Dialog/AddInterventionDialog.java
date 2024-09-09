package com.example.proit_mobile.Dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proit_mobile.Models.Aeroport;
import com.example.proit_mobile.Models.AppUser;
import com.example.proit_mobile.Models.Compagnie;
import com.example.proit_mobile.Models.Comptoire;
import com.example.proit_mobile.Models.Intervention;
import com.example.proit_mobile.Models.InterventionDto;
import com.example.proit_mobile.Models.Projet;
import com.example.proit_mobile.R;
import com.example.proit_mobile.Services.InterventionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInterventionDialog extends Dialog {

    private Spinner compagnieSpinner;
    private Spinner comptoireSpinner;
    private Spinner aeroportSpinner;
    private Spinner projetSpinner;
    private Button submitButton;
    private InterventionService interventionService;
    private String username;
    private List<Compagnie> compagnies;
    private List<Comptoire> comptoires;
    private List<Aeroport> aeroports;
    private List<Projet> projets;

    public AddInterventionDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_intervention);

        compagnieSpinner = findViewById(R.id.compagnieSpinner);
        comptoireSpinner = findViewById(R.id.comptoireSpinner);
        aeroportSpinner = findViewById(R.id.aeroportSpinner);
        projetSpinner = findViewById(R.id.projetSpinner);
        submitButton = findViewById(R.id.submitButton);

        compagnieSpinner.setPrompt("Company");
        comptoireSpinner.setPrompt("Counter");
        aeroportSpinner.setPrompt(" Aeroport");
        projetSpinner.setPrompt(" Project");

        interventionService = new InterventionService(getContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE));
        username = getContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE).getString("username", "");
        Log.d("Usernametest", "Username: " + username);

        loadCompagnies();
        loadComptoires();
        loadAeroports();
        loadProjets();

        submitButton.setOnClickListener(v -> addIntervention());
    }

    private void loadCompagnies() {
        interventionService.getAllcompagnies(new Callback<List<Compagnie>>() {
            @Override
            public void onResponse(Call<List<Compagnie>> call, Response<List<Compagnie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    compagnies = response.body();
                    List<String> compagnieNames = new ArrayList<>();
                    for (Compagnie compagnie : compagnies) {
                        compagnieNames.add(compagnie.getCompagnieName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, compagnieNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    compagnieSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Compagnie>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load compagnies", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadComptoires() {
        interventionService.getAllComptoire(new Callback<List<Comptoire>>() {
            @Override
            public void onResponse(Call<List<Comptoire>> call, Response<List<Comptoire>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    comptoires = response.body();
                    List<String> comptoireNames = new ArrayList<>();
                    for (Comptoire comptoire : comptoires) {
                        comptoireNames.add(comptoire.getComptoireName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, comptoireNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    comptoireSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Comptoire>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load comptoires", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAeroports() {
        interventionService.getAllaeroports(new Callback<List<Aeroport>>() {
            @Override
            public void onResponse(Call<List<Aeroport>> call, Response<List<Aeroport>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    aeroports = response.body();
                    List<String> aeroportNames = new ArrayList<>();
                    for (Aeroport aeroport : aeroports) {
                        aeroportNames.add(aeroport.getAeroportName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, aeroportNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    aeroportSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Aeroport>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load aeroports", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProjets() {
        interventionService.getAllprojects(new Callback<List<Projet>>() {
            @Override
            public void onResponse(Call<List<Projet>> call, Response<List<Projet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    projets = response.body();
                    List<String> projetNames = new ArrayList<>();
                    for (Projet projet : projets) {
                        projetNames.add(projet.getProjetName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, projetNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    projetSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Projet>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load projets", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addIntervention() {
        if (compagnies == null || comptoires == null || aeroports == null || projets == null) {
            Toast.makeText(getContext(), "Data not loaded properly", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a date formatter with the expected format
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        Date now = new Date(); // Current date and time

        // Format dates as strings
        String formattedDate = dateTimeFormat.format(now);

        InterventionDto intervention = new InterventionDto(
                null, // id
                formattedDate, // date
                formattedDate, // heureDebut
                "EN COURS", // status
                null, // heureFin (if needed, format it similarly)
                compagnies.get(compagnieSpinner.getSelectedItemPosition()).getId(),
                username,
                comptoires.get(comptoireSpinner.getSelectedItemPosition()).getId(),
                null, // equipment
                null, // solution
                null, // probleme
                aeroports.get(aeroportSpinner.getSelectedItemPosition()).getId(),
                projets.get(projetSpinner.getSelectedItemPosition()).getId()
        );

        interventionService.addIntervention(intervention, new Callback<InterventionDto>() {
            @Override
            public void onResponse(Call<InterventionDto> call, Response<InterventionDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), "Intervention added successfully", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Failed to add intervention", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InterventionDto> call, Throwable t) {
                Toast.makeText(getContext(), "Intervention added successfully", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}



