package com.playground.NewsAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.playground.NewsAPI.service.TopHeadlinesService;

@SpringBootApplication
public class NewsApiApplication implements CommandLineRunner {
	
	@Autowired
	private TopHeadlinesService thlSvc;

	public static void main(String[] args) {
		SpringApplication.run(NewsApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		thlSvc.getNews("us", "business");
	}

}
