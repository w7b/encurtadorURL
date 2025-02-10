package com.smoothy.encurtador.url.infrastructure.repository;

import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
}
