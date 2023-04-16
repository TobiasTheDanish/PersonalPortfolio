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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GithubRequests {
    private static Map<String, Repository> repos = new HashMap<>();
    private static long rateLimitReset = 0;
    private static boolean rateLimitIsReset() {
        return rateLimitReset < LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public static Map.Entry<Integer, List<Repository>> getRepositorys(String username, String queryParams) {
        if (rateLimitIsReset() && repos != null && repos.values().size() > 0) {
            return new AbstractMap.SimpleEntry<>(200, repos.values().stream().toList());
        }

        repos.clear();
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

            if (response.statusCode() == 200) {
                rateLimitReset = Long.parseLong(response.headers().firstValue("X-RateLimit-Reset").get());

                printRateLimitHeaders("Repos", response);

                JSONArray array = new JSONArray(response.body().toString());

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    if (object.getBoolean("fork")) {
                        continue;
                    }
                    Map.Entry<String, Repository> entry = RepositoryMapper.entryFromJson(object);

                    repos.put(entry.getKey(), entry.getValue());
                }
            }
            return new AbstractMap.SimpleEntry<>(response.statusCode(), repos.values().stream().toList());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return new AbstractMap.SimpleEntry<>(500, repos.values().stream().toList());
        }
    }

    public static Map.Entry<Integer, Repository> getRepoByName(String repoName) {
        if (!repos.isEmpty()) {
            return new AbstractMap.SimpleEntry<>(200, repos.get(repoName));
        }

        String url = APIENDPOINTS.githubRepo(CONSTANTS.GithubUsername(), repoName);

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .header("Authorization", "Bearer" + SECRETS.GIT_TOKEN())
                    .header("Accept", "application/vnd.github+json")
                    .header("X-Github-Api-Version", "2022-11-28")
                    .build();

            HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            printRateLimitHeaders("RepoByName", response);

            if (response.statusCode() == 200) {
                JSONObject object = new JSONObject(response.body().toString());
                return new AbstractMap.SimpleEntry<>(response.statusCode(), RepositoryMapper.fromJson(object));
            }

            return new AbstractMap.SimpleEntry<>(response.statusCode(), null);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return new AbstractMap.SimpleEntry<>(500, null);
        }
    }


    private static LocalDateTime lastRepoFilesRequest = LocalDateTime.MIN;
    private static long timeSinceLastRepoFilesRequest() {
        return Math.abs(ChronoUnit.SECONDS.between(LocalDateTime.now(), lastRepoFilesRequest));
    }

    public static String getRepoFiles(String repoName, String filePath) {
        if (repos != null) {
            if (timeSinceLastRepoFilesRequest() < 3600 || (!repos.isEmpty() && repos.containsKey(repoName))) {
                if (repos.get(repoName).containsFile(filePath)) {
                    return repos.get(repoName).getFileContent(filePath);
                }
            }
        }

        String url = APIENDPOINTS.githubRepoFile(CONSTANTS.GithubUsername(), repoName, filePath);

        try {
            HttpRequest request  = HttpRequest.newBuilder(new URI(url))
                    .GET()
                    .header("Authorization", "Bearer" + SECRETS.GIT_TOKEN())
                    .header("Accept", "application/vnd.github.html")
                    .header("X-Github-Api-Version", "2022-11-28")
                    .build();

            HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            lastRepoFilesRequest = LocalDateTime.now();

            printRateLimitHeaders("RepoFiles", response);

            if (!repos.isEmpty()) {
                repos.get(repoName).addFile(filePath, response.body().toString());
            }

            return response.body().toString();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    private static void printRateLimitHeaders(String requestName, HttpResponse response) {
        String limit = response.headers().firstValue("X-RateLimit-Limit").get();
        String remaining = response.headers().firstValue("X-RateLimit-Remaining").get();
        String used = response.headers().firstValue("X-RateLimit-Used").get();
        String reset = response.headers().firstValue("X-RateLimit-Reset").get();

        System.out.println(requestName + " request:\nLimit: " + limit +"\nRemaining: " + remaining + "\nUsed: " + used + "\nReset: " + reset + "\n");
    }
}
