package com.tobiasthedanish.personalportfolio.Controllers;

import com.tobiasthedanish.personalportfolio.Api.GithubRequests;
import com.tobiasthedanish.personalportfolio.Model.Repository;
import com.tobiasthedanish.personalportfolio.Model.RepositorySortByPushedDate;
import com.tobiasthedanish.personalportfolio.Model.RepositorySortByUpdatedDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String index(Model model) {
        Map.Entry<Integer, List<Repository>> response = GithubRequests.getRepositorys("TobiasTheDanish", "sort=updated");
        if (response.getKey() == 200) {
            List<Repository> repos = new ArrayList<>(response.getValue());
            Collections.sort(repos, new RepositorySortByPushedDate().reversed());
            model.addAttribute("repos", repos);
        }

        return "index";
    }
}
