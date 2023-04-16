package com.tobiasthedanish.personalportfolio.Model;

import java.util.Comparator;

public class RepositorySortByPushedDate implements Comparator<Repository> {
    @Override
    public int compare(Repository o1, Repository o2) {
        return o1.getPushedAt().compareTo(o2.getPushedAt());
    }
}
