package com.demo.codingchallenge.rest;

import com.google.gson.annotations.SerializedName;

public class RepoData {

    @SerializedName("name")
    String name;
    @SerializedName("html_url")
    String url;

}
