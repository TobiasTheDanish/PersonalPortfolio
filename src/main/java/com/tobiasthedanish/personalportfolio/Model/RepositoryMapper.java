package com.tobiasthedanish.personalportfolio.Model;

import org.json.JSONObject;

import java.util.AbstractMap;
import java.util.Map;

public class RepositoryMapper {
    public static Repository fromJson(JSONObject object) {
        Repository repo = new Repository();
        repo.setId(object.getInt("id"));
        repo.setName(object.getString("name"));
        repo.setDescription(object.get("description"));
        repo.setUrl(object.getString("html_url"));
        repo.setCreatedAt(object.getString("created_at"));
        repo.setUpdatedAt(object.getString("updated_at"));
        repo.setPushedAt(object.getString("pushed_at"));
        repo.setStarCount(object.getInt("stargazers_count"));
        repo.setWatchCount(object.getInt("watchers_count"));
        repo.setLanguage(object.get("language"));

        return repo;
    }

    public static Map.Entry<String, Repository> entryFromJson(JSONObject object) {
        Repository repository = fromJson(object);

        return new AbstractMap.SimpleEntry<>(object.getString("name"), repository);
    }
}
