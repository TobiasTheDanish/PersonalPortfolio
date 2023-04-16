package com.tobiasthedanish.personalportfolio.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private int id;
    private String name;
    private String description;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime pushedAt;
    private int starCount;
    private int watchCount;
    private String language;


    private Map<String, String> files = new HashMap<>();

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        int i = createdAt.indexOf("T");
        String dateStr = createdAt.substring(0, i);
        String timeStr = createdAt.substring(++i, createdAt.length()-1);

        LocalDate date = LocalDate.parse(dateStr);
        LocalTime time = LocalTime.parse(timeStr);

        this.createdAt = LocalDateTime.of(date, time);
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        int i = updatedAt.indexOf("T");
        String dateStr = updatedAt.substring(0, i);
        String timeStr = updatedAt.substring(++i, updatedAt.length()-1);

        LocalDate date = LocalDate.parse(dateStr);
        LocalTime time = LocalTime.parse(timeStr);

        this.updatedAt = LocalDateTime.of(date, time);
    }

    public LocalDateTime getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        int i = pushedAt.indexOf("T");
        String dateStr = pushedAt.substring(0, i);
        String timeStr = pushedAt.substring(++i, pushedAt.length()-1);

        LocalDate date = LocalDate.parse(dateStr);
        LocalTime time = LocalTime.parse(timeStr);

        this.pushedAt = LocalDateTime.of(date, time);
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

    public Map<String, String> getFiles() {
        return files;
    }

    public void addFile(String filename, String content) {
        files.put(filename, content);
    }

    public String getFileContent(String filename) {
        return files.get(filename);
    }

    public boolean containsFile(String filename) {
        return files.containsKey(filename);
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
