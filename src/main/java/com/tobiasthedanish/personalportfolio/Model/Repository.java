package com.tobiasthedanish.personalportfolio.Model;

public class Repository {
    private int id;
    private String name;
    private String description;
    private String url;
    private String createdAt;
    private String updatedAt;
    private String pushedAt;
    private int starCount;
    private int watchCount;
    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDescription(Object description) {
        setDescription(description.toString());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLanguage(Object language) {
        setLanguage(language.toString());
    }

    @Override
    public String toString() {
        return "\nRepo {" +
                "\n\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tdescription='" + description + '\'' +
                ", \n\turl='" + url + '\'' +
                ", \n\tcreatedAt='" + createdAt + '\'' +
                ", \n\tupdatedAt='" + updatedAt + '\'' +
                ", \n\tpushedAt='" + pushedAt + '\'' +
                ", \n\tstarCount=" + starCount +
                ", \n\twatchCount=" + watchCount +
                ", \n\tlanguage='" + language + '\'' +
                "\n}";
    }
}
