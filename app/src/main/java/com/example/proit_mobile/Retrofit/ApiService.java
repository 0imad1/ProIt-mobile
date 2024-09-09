package com.example.proit_mobile.Retrofit;

import com.example.proit_mobile.Models.Aeroport;
import com.example.proit_mobile.Models.Compagnie;
import com.example.proit_mobile.Models.Comptoire;
import com.example.proit_mobile.Models.Equipment;
import com.example.proit_mobile.Models.Intervention;
import com.example.proit_mobile.Models.InterventionDto;
import com.example.proit_mobile.Models.LoginResponse;
import com.example.proit_mobile.Models.Probleme;
import com.example.proit_mobile.Models.Projet;
import com.example.proit_mobile.Models.PushNotificationRequest;
import com.example.proit_mobile.Models.Solution;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> login( @Field("username") String username,
                               @Field("password") String password);
    @GET("/technicien/interventions/username")
    Call<List<Intervention>> getInterventionsByUsername( @Query("username") String username);
    @POST("/technicien/intervention/mobile")
    Call<InterventionDto> addIntervention(@Body InterventionDto intervention)  ;
    @POST("/technicien/intervention/fin/mobile")
    Call<InterventionDto> finIntervention(@Body InterventionDto interventionDto);
    @GET("admin/equipementsByprojet")
    Call<List<Equipment>> getAllEquipementsByProject(@Query("id") Long id);


    @GET("admin/comptoires")
    Call<List<Comptoire>> getAllComptoire();
    @GET("admin/compagnies")
    Call<List<Compagnie>> getAllcompagnies();
    @GET("admin/aeroports")
    Call<List<Aeroport>> getAllaeroports();
    @GET("admin/problemes")
    Call<List<Probleme>> getAllproblemes();
    @GET("admin/solutions")
    Call<List<Solution>> getAllsolutions();
    @GET("admin/projets")
    Call<List<Projet>> getAllprojects();
    @PUT("/user/{username}/fcm-token")
    Call<Void> updateUserFcmToken(@Path("username") String username, @Body String fcmToken);




}
