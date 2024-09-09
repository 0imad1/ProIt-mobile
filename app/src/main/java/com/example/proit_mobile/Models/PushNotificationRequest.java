package com.example.proit_mobile.Models;

import com.google.gson.annotations.SerializedName;

public class PushNotificationRequest {
    @SerializedName("token")

    private String token;
    @SerializedName("title")

    private String title;
    @SerializedName("message")

    private String message;
    @SerializedName("topic")

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
