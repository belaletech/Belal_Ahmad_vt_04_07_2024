package com.VidyayatanTechnologies.urlshortenerApplication.service;

import com.VidyayatanTechnologies.urlshortenerApplication.entity.Url;
import com.VidyayatanTechnologies.urlshortenerApplication.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE_URL = "http://localhost:8080/";

    @Transactional
    public String shortenUrl(String longUrl) {
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setCreatedAt(LocalDateTime.now());
        url.setExpiryAt(LocalDateTime.now().plusMonths(10));
        url.setShortUrl(generateShortUrl());

        urlRepository.save(url);

        return BASE_URL + url.getShortUrl();
    }

    @Transactional
    public boolean updateShortUrl(String shortUrl, String longUrl) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            url.setLongUrl(longUrl);
            urlRepository.save(url);
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public String getLongUrl(String shortUrl) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            if (url.getExpiryAt().isAfter(LocalDateTime.now())) {
                return url.getLongUrl();
            }
        }
        return null;
    }

    @Transactional
    public boolean updateExpiry(String shortUrl, int days) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            url.setExpiryAt(url.getExpiryAt().plusDays(days));
            urlRepository.save(url);
            return true;
        } else {
            return false;
        }
    }

    private String generateShortUrl() {
        return new Random().ints(97, 122 + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
