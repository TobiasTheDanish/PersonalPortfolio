package com.tobiasthedanish.personalportfolio.Model;

import java.util.Comparator;

public class RepositorySortByCreatedDate implements Comparator<Repository> {
    @Override
    public int compare(Repository o1, Repository o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }
}
