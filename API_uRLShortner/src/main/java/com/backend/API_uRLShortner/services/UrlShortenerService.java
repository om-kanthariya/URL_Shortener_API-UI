package com.backend.API_uRLShortner.services;

import com.backend.API_uRLShortner.entities.UrlMapping;
import com.backend.API_uRLShortner.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {
    @Autowired
    private UrlMappingRepository repository;

    public UrlMapping shortenUrl(String originalUrl) {
        String code = generateShortCode();
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(code);
        return repository.save(mapping);
    }

    public String getOriginalUrl(String code) {

        Optional<UrlMapping> s = repository.findByShortCode(code);
       return s.get().getOriginalUrl();

//        return repository.findByShortCode(code)
//                .map(UrlMapping::getOriginalUrl)
//                .orElseThrow(() -> new RuntimeException("URL not found"));
    }


    private String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
