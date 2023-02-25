package com.playground.NewsAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playground.NewsAPI.model.Everything;
import com.playground.NewsAPI.service.EverythingService;

@Controller
@RequestMapping("/")
public class EverythingController {
    
    @Autowired
    private EverythingService etSvc;

    @GetMapping("/everything")
    public String getEverything (Model model, @RequestParam String q) {
        List<Everything> etList = etSvc.getEverything(q);
        model.addAttribute("listEverything", etList);
        return "everything";
    }

}
