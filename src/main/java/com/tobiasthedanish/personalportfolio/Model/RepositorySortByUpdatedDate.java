package com.tobiasthedanish.personalportfolio.Model;

import java.util.Comparator;

public class RepositorySortByUpdatedDate implements Comparator<Repository> {
    @Override
    public int compare(Repository o1, Repository o2) {
        return o1.getUpdatedAt().compareTo(o2.getUpdatedAt());
    }
}
