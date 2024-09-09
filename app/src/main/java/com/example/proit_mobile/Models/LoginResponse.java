package com.example.proit_mobile.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("access-token")
    private String accessToken;

    @SerializedName("refresh-token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
