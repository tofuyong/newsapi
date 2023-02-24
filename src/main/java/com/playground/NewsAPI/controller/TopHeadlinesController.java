package com.playground.NewsAPI.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playground.NewsAPI.model.TopHeadlines;
import com.playground.NewsAPI.service.TopHeadlinesService;

@Controller
@RequestMapping("/")
public class TopHeadlinesController {

    @Autowired
    private TopHeadlinesService thlSvc;

    @GetMapping("/topheadlines")
    public String getHeadlines (Model model, @RequestParam String country, @RequestParam String category) {
        Optional<TopHeadlines> opt = thlSvc.getNews(country, category);
        TopHeadlines thl = opt.get();

        model.addAttribute("topheadlines", thl);
        
        return "topheadlines";
    }
    
}
