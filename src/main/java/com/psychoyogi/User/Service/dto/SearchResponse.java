package com.psychoyogi.User.Service.dto;

import java.util.List;

public class SearchResponse<T> {
    private List<T> data;
    private int total;
    private String query;

    public SearchResponse() {}

    public SearchResponse(List<T> data, int total, String query) {
        this.data = data;
        this.total = total;
        this.query = query;
    }

    // Getters and Setters
    public List<T> getData() { return data; }
    public void setData(List<T> data) { this.data = data; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
}