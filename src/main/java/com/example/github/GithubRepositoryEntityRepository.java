package com.example.github;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GithubRepositoryEntityRepository extends JpaRepository<GithubRepositoryEntity, Long> {

    GithubRepositoryEntity findByName(String name);

    // Filter by name containing (case-insensitive)
    List<GithubRepositoryEntity> findByNameContainingIgnoreCase(String name);

    // Optional: Support for sorting via built-in findAll
}