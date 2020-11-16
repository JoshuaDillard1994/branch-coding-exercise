package com.demo.codingchallenge.rest;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class GithubData {

    @SerializedName("user_name")
    String userName;
    @SerializedName("display_name")
    String displayName;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("geo_location")
    String geoLocation;
    @SerializedName("email")
    String email;
    @SerializedName("url")
    String url;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("repos")
    List<RepoData> repos;

}
