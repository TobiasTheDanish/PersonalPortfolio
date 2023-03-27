package com.tobiasthedanish.personalportfolio.Controllers;

import com.tobiasthedanish.personalportfolio.Api.GithubRequests;
import com.tobiasthedanish.personalportfolio.Model.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Repo")
public class RepoController {

    @GetMapping("")
    public String getRepo(Model model, @RequestParam String name) {
        Repository repo = GithubRequests.getRepoByName(name, "TobiasTheDanish");
        model.addAttribute("repository", repo);

        return "repo";
    }
}
