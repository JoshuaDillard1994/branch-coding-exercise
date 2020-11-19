package com.demo.codingchallenge.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GithubController {

    DataConnector connector = new DataConnector();
    Gson gson = new GsonBuilder().serializeNulls().create();

    @GetMapping(value = "/userData/{userName}")
    public ResponseEntity<?> getGithubData(@PathVariable("userName") String userName) {

        try {

            UserData userData = connector.getUserData(userName);
            List<RepoData> repoData = connector.getRepoData(userName);

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

        } catch (IOException e){
            return new ResponseEntity<>("An error occurred while retrieving user data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
