package com.upwork.webforumapp.dto.thread;

import com.upwork.webforumapp.dto.ApiResponse;

import java.util.List;
import java.util.Map;

public class SearchThreadDto extends ApiResponse {
    Map<String, List<String>> searchResults;

    public SearchThreadDto() {
    }

    public SearchThreadDto(Map<String, List<String>> searchResults) {
        this.searchResults = searchResults;
    }

    public SearchThreadDto(String status, String message, Map<String, List<String>> searchResults) {
        super(status, message);
        this.searchResults = searchResults;
    }

    public Map<String, List<String>> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(Map<String, List<String>> searchResults) {
        this.searchResults = searchResults;
    }
}
