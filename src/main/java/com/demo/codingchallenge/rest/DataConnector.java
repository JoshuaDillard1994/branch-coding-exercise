package com.demo.codingchallenge.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataConnector {
    private static final String githubUrl = "https://api.github.com/users";

    Gson gson = new GsonBuilder().serializeNulls().create();
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    UserData getUserData(String userName) throws IOException {

        Request request = new Request.Builder()
                .url(String.format("%s/%s", githubUrl, userName))
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if(200 != (response.code())){
            throw new IOException();
        }

        return gson.fromJson(response.body().string(), UserData.class);

    }

    List<RepoData> getRepoData(String userName) throws IOException{

        Request request = new Request.Builder()
                .url(String.format("%s/%s/repos", githubUrl, userName))
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if(200 != (response.code())){
            throw new IOException();
        }

        RepoData[] repoDataArray = gson.fromJson(response.body().string(), RepoData[].class);
        List<RepoData> repoDataList = new ArrayList<>();

        Collections.addAll(repoDataList, repoDataArray);
        return repoDataList;

    }

}
