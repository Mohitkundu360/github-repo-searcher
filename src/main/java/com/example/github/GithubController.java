package com.example.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GithubController {

    @Autowired
    private GithubService githubService;

    // POST API: Search GitHub Repositories and Save to Database
    @PostMapping("/search")
    public ResponseEntity<List<GithubRepository>> searchRepositories(@RequestBody GithubSearchRequest request) {
        List<GithubRepository> repositories = githubService.searchRepositories(request.getQuery());
        return ResponseEntity.ok(repositories);
    }

    // GET API: Retrieve All Repositories with Optional Filtering & Sorting
    @GetMapping("/repositories")
    public ResponseEntity<List<GithubRepositoryEntity>> getAllRepositories(
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String sortBy) {

        List<GithubRepositoryEntity> repositories = githubService.getAllRepositories(nameFilter, sortBy);
        return ResponseEntity.ok(repositories);
    }
}