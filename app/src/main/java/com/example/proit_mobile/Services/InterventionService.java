package com.example.proit_mobile.Services;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.proit_mobile.Models.Aeroport;
import com.example.proit_mobile.Models.Compagnie;
import com.example.proit_mobile.Models.Comptoire;
import com.example.proit_mobile.Models.Equipment;
import com.example.proit_mobile.Models.Intervention;
import com.example.proit_mobile.Models.InterventionDto;
import com.example.proit_mobile.Models.Probleme;
import com.example.proit_mobile.Models.Projet;
import com.example.proit_mobile.Models.Solution;
import com.example.proit_mobile.Retrofit.ApiService;
import com.example.proit_mobile.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterventionService {

    private ApiService apiService;

    public InterventionService(SharedPreferences sharedPreferences) {
        this.apiService = new RetrofitService(sharedPreferences).getRetrofit().create(ApiService.class);
    }

    public void getInterventions(String username, final Callback<List<Intervention>> callback) {
        Call<List<Intervention>> call = apiService.getInterventionsByUsername(username);
        call.enqueue(new Callback<List<Intervention>>() {
            @Override
            public void onResponse(Call<List<Intervention>> call, Response<List<Intervention>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Intervention>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }


    public void addIntervention(InterventionDto intervention, final Callback<InterventionDto> callback) {
        Call<InterventionDto> call = apiService.addIntervention(intervention);
        call.enqueue(new Callback<InterventionDto>() {
            @Override
            public void onResponse(Call<InterventionDto> call, Response<InterventionDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to add intervention "));
                }
            }

            @Override
            public void onFailure(Call<InterventionDto> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }

    public void finntervention(InterventionDto intervention, final Callback<InterventionDto> callback) {
        Call<InterventionDto> call = apiService.finIntervention(intervention);
        call.enqueue(new Callback<InterventionDto>() {
            @Override
            public void onResponse(Call<InterventionDto> call, Response<InterventionDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to finish intervention "));
                }
            }

            @Override
            public void onFailure(Call<InterventionDto> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }


    public void getAllcompagnies(final Callback<List<Compagnie>> callback) {
        Call<List<Compagnie>> call = apiService.getAllcompagnies();
        call.enqueue(new Callback<List<Compagnie>>() {
            @Override
            public void onResponse(Call<List<Compagnie>> call, Response<List<Compagnie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Compagnie>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }
    public void getAllSolutions(final Callback<List<Solution>> callback) {
        Call<List<Solution>> call = apiService.getAllsolutions();
        call.enqueue(new Callback<List<Solution>>() {
            @Override
            public void onResponse(Call<List<Solution>> call, Response<List<Solution>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Solution>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }
    public void getAllProblemes(final Callback<List<Probleme>> callback) {
        Call<List<Probleme>> call = apiService.getAllproblemes();
        call.enqueue(new Callback<List<Probleme>>() {
            @Override
            public void onResponse(Call<List<Probleme>> call, Response<List<Probleme>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Probleme>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }
    public void getAllEquipement(Long id ,final Callback<List<Equipment>> callback) {
        Call<List<Equipment>> call = apiService.getAllEquipementsByProject(id);
        call.enqueue(new Callback<List<Equipment>>() {
            @Override
            public void onResponse(Call<List<Equipment>> call, Response<List<Equipment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Equipment>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }

    public void getAllComptoire(final Callback<List<Comptoire>> callback) {
        Call<List<Comptoire>> call = apiService.getAllComptoire();
        call.enqueue(new Callback<List<Comptoire>>() {
            @Override
            public void onResponse(Call<List<Comptoire>> call, Response<List<Comptoire>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Comptoire>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }

    public void getAllaeroports(final Callback<List<Aeroport>> callback) {
        Call<List<Aeroport>> call = apiService.getAllaeroports();
        call.enqueue(new Callback<List<Aeroport>>() {
            @Override
            public void onResponse(Call<List<Aeroport>> call, Response<List<Aeroport>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Aeroport>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }

    public void getAllprojects(final Callback<List<Projet>> callback) {
        Call<List<Projet>> call = apiService.getAllprojects();
        call.enqueue(new Callback<List<Projet>>() {
            @Override
            public void onResponse(Call<List<Projet>> call, Response<List<Projet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse(call, response);
                } else {
                    Log.e("InterventionService", "Response Error: " + response.message());
                    callback.onFailure(call, new Throwable("Failed to fetch data"));
                }
            }

            @Override
            public void onFailure(Call<List<Projet>> call, Throwable t) {
                Log.e("InterventionService", "Failure: " + t.getMessage());
                callback.onFailure(call, t);
            }
        });
    }
}
