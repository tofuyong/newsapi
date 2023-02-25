package com.playground.NewsAPI.model;

import java.util.LinkedList;
import java.util.List;

public class TopHeadlines {
    private String country;
    private String category;
    private String source;
    private String author;
    private String title;
    private List<String> top10List = new LinkedList<>();

    public String getCountry() {return this.country;}
    public void setCountry(String country) {this.country = country;}
    
    public String getCategory() {return this.category;}
    public void setCategory(String category) {this.category = category;}

    public String getSource() {return this.source;}
    public void setSource(String source) {this.source = source;}

    public String getAuthor() {return this.author;}
    public void setAuthor(String author) {this.author = author;}

    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}

    public List<String> getTop10List() {return this.top10List;}
    public void setTop10List(List<String> top10List) {this.top10List = top10List;}
    public void addTop10List(String news) { this.top10List.add(news); }

}
