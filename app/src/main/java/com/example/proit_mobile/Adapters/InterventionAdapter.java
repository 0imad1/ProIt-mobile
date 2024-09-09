package com.example.proit_mobile.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.proit_mobile.Dialog.FinInterventionDialog;
import com.example.proit_mobile.Models.Intervention;
import com.example.proit_mobile.Models.InterventionDto;
import com.example.proit_mobile.R;
import com.example.proit_mobile.Services.InterventionService;

import java.util.ArrayList;
import java.util.List;

public class InterventionAdapter extends RecyclerView.Adapter<InterventionAdapter.InterventionViewHolder> {

    private List<Intervention> interventions = new ArrayList<>();
    private InterventionService interventionService;
    private Context context;
    private AppCompatActivity activity;

    public InterventionAdapter(InterventionService interventionService,AppCompatActivity activity) {
        this.interventionService = interventionService;
        this .activity=activity;
    }

    @NonNull
    @Override
    public InterventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intervention, parent, false);
        return new InterventionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterventionViewHolder holder, int position) {
        Intervention intervention = interventions.get(position);

        holder.statusTextView.setText(intervention.getStatus() != null ? intervention.getStatus() : "Unknown");
        holder.dateTextView.setText(intervention.getDate() != null ? intervention.getDate() : "Unknown");
        holder.heureDebutTextView.setText(intervention.getHeureDebut() != null ? intervention.getHeureDebut() : "Unknown");
        holder.heureFinTextView.setText(intervention.getHeureFin() != null ? intervention.getHeureFin() : "Unknown");
        holder.compagnieTextView.setText(intervention.getCompagnie() != null ? intervention.getCompagnie().getCompagnieName() : "Unknown");
        holder.equipmentTextView.setText(intervention.getEquipment() != null ? intervention.getEquipment().getEquipementName() : "Unknown");
        holder.solutionTextView.setText(intervention.getSolution() != null ? intervention.getSolution().getLibelle() : "Unknown");
        holder.problemeTextView.setText(intervention.getProbleme() != null ? intervention.getProbleme().getLibelle() : "Unknown");
        holder.comptoireTextView.setText(intervention.getComptoire() != null ? intervention.getComptoire().getComptoireName() : "Unknown");
        holder.projetTextView.setText(intervention.getProjet() != null ? intervention.getProjet().getProjetName() : "Unknown");
        holder.aeroportTextView.setText(intervention.getAeroport() != null ? intervention.getAeroport().getAeroportName() : "Unknown");

        holder.finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convert Intervention to InterventionDto
                InterventionDto interventionDto = new InterventionDto(
                        intervention.getId(),
                        intervention.getDate().toString(),
                        intervention.getHeureDebut().toString(),
                        intervention.getStatus().toString(),
                        null,
                        intervention.getCompagnie().getId(),
                        intervention.getAppUser().getUsername(),
                        intervention.getComptoire().getId(),
                        null,
                        null,
                        null,
                        intervention.getAeroport().getId(),
                        intervention.getProjet().getId()
                );
                Log.d("testIntervention", "id: " + interventionDto.getProjet());
                Log.d("testIntervention", "id: " + interventionDto.getDate().toString());
                Log.d("testIntervention", "id: " + interventionDto.getHeureDebut().toString());
                Log.d("testIntervention", "id: " + interventionDto.getAeroport());


                // Show the dialog
                FinInterventionDialog dialog = new FinInterventionDialog(activity, interventionDto,interventionService);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return interventions.size();
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
        notifyDataSetChanged();
    }

    public static class InterventionViewHolder extends RecyclerView.ViewHolder {
        TextView statusTextView;
        TextView dateTextView;
        TextView heureDebutTextView;
        TextView heureFinTextView;
        TextView compagnieTextView;
        TextView equipmentTextView;
        TextView solutionTextView;
        TextView problemeTextView;
        TextView comptoireTextView;
        TextView projetTextView;
        TextView aeroportTextView;
        ImageView finishButton;

        public InterventionViewHolder(@NonNull View itemView) {
            super(itemView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            heureDebutTextView = itemView.findViewById(R.id.heureDebutTextView);
            heureFinTextView = itemView.findViewById(R.id.heureFinTextView);
            compagnieTextView = itemView.findViewById(R.id.compagnieTextView);
            equipmentTextView = itemView.findViewById(R.id.equipmentTextView);
            solutionTextView = itemView.findViewById(R.id.solutionTextView);
            problemeTextView = itemView.findViewById(R.id.problemeTextView);
            comptoireTextView = itemView.findViewById(R.id.comptoireTextView);
            projetTextView = itemView.findViewById(R.id.projetTextView);
            aeroportTextView = itemView.findViewById(R.id.aeroportTextView);
            finishButton = itemView.findViewById(R.id.finishButton);
        }
    }
}
