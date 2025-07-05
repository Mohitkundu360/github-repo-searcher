package com.example.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GithubService {

    @Autowired
    private GithubRepositoryEntityRepository repository;

    // Search and Save
    public List<GithubRepository> searchRepositories(String query) {
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Query parameter cannot be empty.");
        }

        String url = "https://api.github.com/search/repositories?q=" + query;
        RestTemplate restTemplate = new RestTemplate();

        try {
            GithubResponse response = restTemplate.getForObject(url, GithubResponse.class);
            if (response == null || response.getItems().isEmpty()) {
                throw new RuntimeException("No repositories found for the given query.");
            }

            List<GithubRepository> repositories = response.getItems();

            for (GithubRepository repo : repositories) {
                GithubRepositoryEntity existingRepo = repository.findByName(repo.getName());

                if (existingRepo == null) {
                    GithubRepositoryEntity newRepo = new GithubRepositoryEntity();
                    newRepo.setName(repo.getName());
                    newRepo.setHtmlUrl(repo.getHtml_url());
                    newRepo.setDescription(repo.getDescription());
                    repository.save(newRepo);
                } else {
                    existingRepo.setHtmlUrl(repo.getHtml_url());
                    existingRepo.setDescription(repo.getDescription());
                    repository.save(existingRepo);
                }
            }

            return repositories;

        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching data from GitHub API: " + e.getMessage());
        }
    }

    // Retrieve with filtering and sorting
    public List<GithubRepositoryEntity> getAllRepositories(String nameFilter, String sortBy) {
        if (nameFilter != null && !nameFilter.isEmpty()) {
            List<GithubRepositoryEntity> filtered = repository.findByNameContainingIgnoreCase(nameFilter);
            if (filtered.isEmpty()) {
                throw new RuntimeException("No repositories found for the given filter.");
            }
            return filtered;
        } else if (sortBy != null && !sortBy.isEmpty()) {
            return repository.findAll(Sort.by(sortBy).ascending());
        } else {
            return repository.findAll();
        }
    }
}