package com.bartoszjd;


import java.io.IOException;
import java.util.UUID;


import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bartoszjedrzejewski on 30/07/2016.
 */
@RestController
@RequestMapping("/github")
public class GithubStatsController {

    private static final Logger log = Logger.getLogger(GithubStatsController.class);

    @Autowired
    private GithubConfig githubConfig;

    @RequestMapping()
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return uid.toString();
    }

    @RequestMapping(value="stats/{organisation}")
    public ResponseEntity organisationRepos(@PathVariable("organisation") String organisation, Model model, HttpSession session) throws IOException {
        GitHubClient client = new GitHubClient();
        client.setCredentials(githubConfig.getUsername(), githubConfig.getPassword());
        RepositoryService service = new RepositoryService();
        for (Repository repo : service.getRepositories(organisation))
            System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }



}
