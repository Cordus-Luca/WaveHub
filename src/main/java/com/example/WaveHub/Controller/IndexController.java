package com.example.WaveHub.Controller;

import org.springframework.ui.Model;
import com.example.WaveHub.ServiceLayer.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final StorageService storageService;

    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getHomepage(Model model) {

        // MVC - Model, View, Controller

        model.addAttribute("songFileNames", storageService.getSongFileNames());

        return "index";
    }

}
