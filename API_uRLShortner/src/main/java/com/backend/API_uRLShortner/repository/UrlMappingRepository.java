package com.backend.API_uRLShortner.repository;

import com.backend.API_uRLShortner.entities.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortCode(String shortCode);
//    Optional<UrlMapping> findAllShortCode();
}
