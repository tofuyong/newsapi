package com.playground.NewsAPI.service;

import java.io.StringReader;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.playground.NewsAPI.model.TopHeadlines;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import org.springframework.http.ResponseEntity;

@Service
public class TopHeadlinesService {

    public final static String URL = "https://newsapi.org/v2/top-headlines";

    @Value("${newsapi.key}")
    private String apiKey;

    public Optional<TopHeadlines> getNews(String country, String category) {

        String url = UriComponentsBuilder.fromUriString(URL)
                    .queryParam("country", country)
                    .queryParam("category", category)
                    .queryParam("apiKey", apiKey)
                    .toUriString();

        System.out.printf("NEVER DO THIS!!!!: Top Headlines URL: %s\n", url);

        RequestEntity<Void> req = RequestEntity.get(url)
                        .header("X-Api-Key", apiKey)
                        .accept(MediaType.APPLICATION_JSON)
                        .build();

        //Make call to News API
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        String payload = "";
        int statusCode = 500;

        try {
            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            statusCode = resp.getStatusCode().value();
        } catch (HttpClientErrorException ex) {
            payload = ex.getResponseBodyAsString();
            statusCode = ex.getStatusCode().value();
            return Optional.empty();
        } finally {
            System.out.printf(">>> status code: %d\n", statusCode);
            // System.out.printf(">>> payload: \n%s\n", payload);
        }

        TopHeadlines thl = new TopHeadlines();
        thl.setCountry(country);
        thl.setCategory(category);

        // Parse result to TopHeadlines
        JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject newsJson = reader.readObject();
		JsonArray newsArray = newsJson.getJsonArray("articles");

        for (int i = 0; i < 10; i++) {
            JsonObject jo = newsArray.getJsonObject(i);
    
            // Get title of news
            String title = "%s".formatted(jo.getString("title"));
            thl.setTitle(title);
            thl.addTop10List(title);
    
            // Get source of news
            JsonObject source = jo.getJsonObject("source");
            String newsSource = "%s".formatted(source.getString("name"));
            thl.setSource(newsSource);
    
            // Get author of news
            // String author = "%s".formatted(jo.getString("author"));
            // thl.setAuthor(author);
            // System.out.println("Author = " + author);
    
            System.out.println(i+1 + ". " + title); //testing
            System.out.println("by " + newsSource); //testing

        }
        return Optional.of(thl);
    }
}
