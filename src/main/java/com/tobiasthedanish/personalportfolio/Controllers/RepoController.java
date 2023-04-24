package com.tobiasthedanish.personalportfolio.Controllers;

import com.tobiasthedanish.personalportfolio.Api.GithubRequests;
import com.tobiasthedanish.personalportfolio.Model.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/Repo")
public class RepoController {

    @GetMapping("")
    public String getRepo(Model model, @RequestParam String name) {
        String readme = GithubRequests.getRepoFiles(name, "README.md");
        Map.Entry<Integer, Repository> response = GithubRequests.getRepoByName(name);

        if (response.getKey() == 200) {
            Repository repo = response.getValue();
            if (readme != null && !readme.contains("\"message\":\"Not Found\"")) {
                model.addAttribute("readme", readme);
            }
            model.addAttribute("repository", repo);
        }

        return "repo";
    }
}
