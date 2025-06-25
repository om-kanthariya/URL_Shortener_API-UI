package com.backend.API_uRLShortner.controller;

import com.backend.API_uRLShortner.entities.UrlMapping;
import com.backend.API_uRLShortner.services.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService service;

    @PostMapping("/shorten")
    public ResponseEntity<UrlMapping> shortenUrl(@RequestBody Map<String, String> request) {
        String longUrl = request.get("originalUrl");
        return ResponseEntity.ok(service.shortenUrl(longUrl));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<String> redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String originalUrl = service.getOriginalUrl(shortCode);
//        response.sendRedirect(originalUrl);
        return ResponseEntity.ok(originalUrl);
    }


}
