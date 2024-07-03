package com.VidyayatanTechnologies.urlshortenerApplication.controller;

import com.VidyayatanTechnologies.urlshortenerApplication.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String longUrl) {
        return urlService.shortenUrl(longUrl);
    }

    @PostMapping("/update")
    public boolean updateShortUrl(@RequestParam String shortUrl, @RequestParam String longUrl) {
        return urlService.updateShortUrl(shortUrl, longUrl);
    }

    @GetMapping("/{shortUrl}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortUrl) throws IOException {
        String longUrl = urlService.getLongUrl(shortUrl);
        if (longUrl != null) {
            response.sendRedirect(longUrl);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found");
        }
    }

    @PostMapping("/update_expiry")
    public boolean updateExpiry(@RequestParam String shortUrl, @RequestParam int days) {
        return urlService.updateExpiry(shortUrl, days);
    }
}
