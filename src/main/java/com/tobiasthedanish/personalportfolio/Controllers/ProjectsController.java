package com.tobiasthedanish.personalportfolio.Controllers;

import com.tobiasthedanish.personalportfolio.Api.GithubRequests;
import com.tobiasthedanish.personalportfolio.Model.Repository;
import com.tobiasthedanish.personalportfolio.Model.RepositorySortByPushedDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/projects")
public class ProjectsController {

    @GetMapping()
    public String getProjects(Model model) {
        Map.Entry<Integer, List<Repository>> response = GithubRequests.getRepositorys("TobiasTheDanish", "sort=updated");
        if (response.getKey() == 200) {
            List<Repository> repos = new ArrayList<>(response.getValue());
            repos.sort(new RepositorySortByPushedDate().reversed());
            model.addAttribute("repos", repos);
        }

        return "projects";
    }
}
