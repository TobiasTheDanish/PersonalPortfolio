package com.tobiasthedanish.personalportfolio.Controllers;

import com.tobiasthedanish.personalportfolio.Api.GithubRequests;
import com.tobiasthedanish.personalportfolio.Model.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String index(Model model) {
        List<Repository> repos = GithubRequests.getRepositorys("TobiasTheDanish", "sort=updated");

        model.addAttribute("repos", repos);
        return "index";
    }
}
