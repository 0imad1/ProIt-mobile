package com.example.proit_mobile.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proit_mobile.Models.Equipment;
import com.example.proit_mobile.Models.InterventionDto;
import com.example.proit_mobile.Models.Probleme;
import com.example.proit_mobile.Models.Solution;
import com.example.proit_mobile.R;
import com.example.proit_mobile.Services.InterventionService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinInterventionDialog extends Dialog {
    private Spinner equipementSpinner;
    private Spinner problemSpinner;
    private Spinner solutionSpinner;
    private Button submitButton;
    private InterventionService interventionService;
    private InterventionDto intervention;
    private List<Solution> solutions;
    private List<Probleme> problemes;
    private Long projectId;
    private List<Equipment> equipments;
    private AppCompatActivity activity;

    public FinInterventionDialog(AppCompatActivity activity, InterventionDto intervention, InterventionService interventionService) {
        super(activity);
        this.interventionService=interventionService;
        this.intervention = intervention;
        this.activity=activity;
        projectId=intervention.getProjet();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fin_intervention);

        equipementSpinner = findViewById(R.id.equipementSpinner);
        problemSpinner = findViewById(R.id.problemeSpinner);
        solutionSpinner = findViewById(R.id.solutionSpinner);
        submitButton = findViewById(R.id.submitButton);

        loadEquipement();
        loadProblemes();
        loadSolutions();

        submitButton.setOnClickListener(v -> finishIntervention());
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        show();
    }


    private void loadSolutions() {
        interventionService.getAllSolutions(new Callback<List<Solution>>() {
            @Override
            public void onResponse(Call<List<Solution>> call, Response<List<Solution>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    solutions = response.body();
                    List<String> libelle = new ArrayList<>();
                    for (Solution solution : solutions) {
                        libelle.add(solution.getLibelle());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, libelle);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    solutionSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Solution>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load solutions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProblemes() {
        interventionService.getAllProblemes(new Callback<List<Probleme>>() {
            @Override
            public void onResponse(Call<List<Probleme>> call, Response<List<Probleme>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    problemes = response.body();
                    List<String> libelle = new ArrayList<>();
                    for (Probleme probleme : problemes) {
                        libelle.add(probleme.getLibelle());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, libelle);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    problemSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Probleme>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load problems", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadEquipement() {
        interventionService.getAllEquipement(intervention.getId(), new Callback<List<Equipment>>() {

            @Override
            public void onResponse(Call<List<Equipment>> call, Response<List<Equipment>> response) {
                Log.d("projecttest", "project: " + intervention.getProjet() );
                if (response.isSuccessful() && response.body() != null) {
                    equipments = response.body();
                    List<String> equipementName = new ArrayList<>();
                    for (Equipment equipment : equipments) {
                        equipementName.add(equipment.getEquipementName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, equipementName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    equipementSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Equipment>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load equipment", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void finishIntervention() {
        if (solutions == null || problemes == null || equipments == null) {
            Toast.makeText(getContext(), "Data not loaded properly", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        Date now = new Date(); // Current date and time

        // Format dates as strings
        String formattedDate = dateTimeFormat.format(now);

        // Get the selected solution, problem, and equipment from the lists
        Solution selectedSolution = solutions.get(solutionSpinner.getSelectedItemPosition());
        Probleme selectedProbleme = problemes.get(problemSpinner.getSelectedItemPosition());
        Equipment selectedEquipment = equipments.get(equipementSpinner.getSelectedItemPosition());

        // Set the IDs of the intervention object
        intervention.setEquipment(selectedEquipment.getId());
        intervention.setProbleme(selectedProbleme.getId());
        intervention.setSolution(selectedSolution.getId());

        intervention.setHeureFin(formattedDate);
        intervention.setStatus("FERMER"); // Use the appropriate value for your backend

        interventionService.finntervention(intervention, new Callback<InterventionDto>() {
            @Override
            public void onResponse(Call<InterventionDto> call, Response<InterventionDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), "Intervention finished successfully", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Failed to finish intervention", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InterventionDto> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to finish intervention", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
