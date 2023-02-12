package com.example.ssiach8.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class VideoController {

    @GetMapping("/video/{country}/{language}")
    public String video(
        @PathVariable String country,
        @PathVariable String language
    ) {
        return "Video allowed for " + country + " " + language;
    }
}
