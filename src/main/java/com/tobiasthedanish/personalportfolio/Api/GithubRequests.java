package com.tobiasthedanish.personalportfolio.Api;

import com.tobiasthedanish.personalportfolio.Model.Repository;
import com.tobiasthedanish.personalportfolio.Model.RepositoryMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GithubRequests {
    public static List<Repository> getRepositorys(String username, String queryParams) {
        List<Repository> repos = new ArrayList<>();
        String url = APIENDPOINTS.githubRepos(username);
        if (queryParams != null) {
            url += "?" + queryParams;
        }

        try {
            HttpRequest request  = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .header("Authorization", "Bearer" + SECRETS.GIT_TOKEN())
                    .header("Accept", "application/vnd.github+json")
                    .header("X-Github-Api-Version", "2022-11-28")
                    .build();

            HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JSONArray array = new JSONArray(response.body().toString());

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                if (object.getBoolean("fork")) {
                    continue;
                }

                repos.add(RepositoryMapper.fromJson(object));
            }

            return repos;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Repository getRepoByName(String repoName, String username) {
        String url = APIENDPOINTS.githubRepo(username, repoName);

        try {
            HttpRequest request  = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .header("Authorization", "Bearer" + SECRETS.GIT_TOKEN())
                    .header("Accept", "application/vnd.github+json")
                    .header("X-Github-Api-Version", "2022-11-28")
                    .build();

            HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject object = new JSONObject(response.body().toString());

            return RepositoryMapper.fromJson(object);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
