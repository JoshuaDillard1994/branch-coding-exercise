package com.demo.codingchallenge.rest;

import com.google.gson.annotations.SerializedName;
public class UserData {

    @SerializedName("login")
    String userName;
    @SerializedName("avatar_url")
    String avatar;
    @SerializedName("url")
    String url;
    @SerializedName("name")
    String displayName;
    @SerializedName("location")
    String geoLocation;
    @SerializedName("email")
    String email;
    @SerializedName("created_at")
    String createdAt;


}