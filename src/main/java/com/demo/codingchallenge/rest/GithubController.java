package com.demo.codingchallenge.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class GithubController {
    private static final String githubUrl = "https://api.github.com/users";

    OkHttpClient client = new OkHttpClient().newBuilder().build();
    Gson gson = new GsonBuilder().serializeNulls().create();

    @GetMapping(value = "/userData/{userName}")
    public ResponseEntity<?> getGithubData(@PathVariable("userName") String userName) throws IOException {

        UserData userData = getUserData(userName);
        List<RepoData> repoData = getRepoData(userName);

        GithubData githubData = new GithubData();
        githubData.userName = userData.userName;
        githubData.displayName = userData.displayName;
        githubData.avatar = userData.avatar;
        githubData.geoLocation = userData.geoLocation;
        githubData.email = userData.email;
        githubData.url = userData.url;
        githubData.createdAt = userData.createdAt;
        githubData.repos = repoData;

        return new ResponseEntity<>(gson.toJson(githubData), HttpStatus.OK);

    }

    UserData getUserData(String userName) throws IOException {

        Request request = new Request.Builder()
                .url(String.format("%s/%s", githubUrl, userName))
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        return gson.fromJson(response.body().string(), UserData.class);

    }

    List<RepoData> getRepoData(String userName) throws IOException{

        Request request = new Request.Builder()
                .url(String.format("%s/%s/repos", githubUrl, userName))
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        RepoData[] repoDataArray = gson.fromJson(response.body().string(), RepoData[].class);
        List<RepoData> repoDataList = new ArrayList<>();

        Collections.addAll(repoDataList, repoDataArray);
        return repoDataList;

    }


}
