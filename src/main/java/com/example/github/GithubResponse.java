package com.example.github;

import java.util.List;

public class GithubResponse {
    private List<GithubRepository> items;

    public List<GithubRepository> getItems() {
        return items;
    }

    public void setItems(List<GithubRepository> items) {
        this.items = items;
    }
}