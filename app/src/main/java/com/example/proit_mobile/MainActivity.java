package com.example.proit_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.proit_mobile.Dialog.AddInterventionDialog;
import com.example.proit_mobile.Models.Intervention;
import com.example.proit_mobile.Models.PushNotificationRequest;
import com.example.proit_mobile.Services.InterventionService;
import com.example.proit_mobile.Adapters.InterventionAdapter;
import com.example.proit_mobile.Retrofit.ApiService;
import com.example.proit_mobile.Retrofit.RetrofitService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView usernameTextView;
    private ImageView logoutIcon;
    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private TextView emptyView;
    private InterventionAdapter adapter;
    private InterventionService interventionService;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean showEnCours = true;
    private Button toggleStatusButton;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main);

        // Check if the user is logged in
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_signed_up", false);

        if (!isLoggedIn) {
            // Redirect to LoginActivity if not logged in
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return; // Exit onCreate
        }

        // Initialize UI components
        usernameTextView = findViewById(R.id.username);
        logoutIcon = findViewById(R.id.logoutIcon);
        recyclerView = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.emptyView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        toggleStatusButton = findViewById(R.id.toggleStatusButton);
        toggleStatusButton.setOnClickListener(v -> toggleStatus());
        progressBar = findViewById(R.id.progressBar);

        username = sharedPreferences.getString("username", "User");
        usernameTextView.setText(username);

        interventionService = new InterventionService(sharedPreferences);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InterventionAdapter(interventionService, this);
        recyclerView.setAdapter(adapter);

        loadInterventions(username);

        Button addInterventionButton = findViewById(R.id.addInterventionButton);
        addInterventionButton.setOnClickListener(v -> showAddInterventionDialog());

        // Set up SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Reload interventions when the user swipes down to refresh
            loadInterventions(username);
            new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 1000);
        });

        // Set up logout icon click listener
        logoutIcon.setOnClickListener(v -> performLogout());

        // Fetch and send the FCM token after the user is logged in
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                return;
            }


            // Get the FCM token
            String token = task.getResult();

            // Check if token is revoked
            if (task.isCanceled()) {
                Log.d(TAG, "Token is revoked");
            } else {
                Log.d(TAG, "Token is not revoked");
            }

            // Send the token to your server to associate with the logged-in user
            sendTokenToServer(token);
        });
    }

    private void sendTokenToServer(String token) {
        // Make an API call to send the token to your server and associate it with the user
        RetrofitService retrofitService = new RetrofitService(getSharedPreferences("user_prefs", MODE_PRIVATE));
        ApiService apiService = retrofitService.getRetrofit().create(ApiService.class);

        Call<Void> call = apiService.updateUserFcmToken(username, token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "FCM token updated successfully.");
                } else {
                    Log.e(TAG, "Failed to update FCM token, Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Failed to update FCM token", t);
            }
        });
    }




    private void toggleStatus() {
        showEnCours = !showEnCours;
        toggleStatusButton.setText(showEnCours ? "Toggle Status (EN COURS)" : "Toggle Status (FERMER)");
        loadInterventions(username);
    }

    private void showAddInterventionDialog() {
        AddInterventionDialog dialog = new AddInterventionDialog(this);
        dialog.show();
    }

    private void loadInterventions(String username) {
        progressBar.setVisibility(View.VISIBLE);

        interventionService.getInterventions(username, new retrofit2.Callback<List<Intervention>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Intervention>> call, retrofit2.Response<List<Intervention>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Intervention> interventions = response.body();
                    List<Intervention> filteredInterventions = new ArrayList<>();

                    for (Intervention intervention : interventions) {
                        if (showEnCours && intervention.getStatus().equals("ENCOURS")) {
                            filteredInterventions.add(intervention);
                        } else if (!showEnCours && intervention.getStatus().equals("FERMER")) {
                            filteredInterventions.add(intervention);
                        }
                    }

                    if (filteredInterventions.isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                        adapter.setInterventions(filteredInterventions);
                    }
                } else {
                    Log.e(TAG, "Failed to load interventions, Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Intervention>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "Failed to load interventions", t);
            }
        });
    }


    private void performLogout() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.remove("refreshToken");
        editor.remove("is_signed_up");
        editor.remove("username");
        editor.apply();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(MainActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
    }
}
