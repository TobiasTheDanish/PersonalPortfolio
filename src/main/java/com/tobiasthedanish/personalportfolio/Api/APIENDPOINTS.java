package com.tobiasthedanish.personalportfolio.Api;

public class APIENDPOINTS {
    public static String githubRepos(String username) {
        return "https://api.github.com/users/" + username + "/repos";
    }

    public static String githubRepo(String username, String repoName) {
        return "https://api.github.com/repos/" + username + "/" + repoName;
    }
}
