package com.smoothy.encurtador.url.infrastructure.repository;

import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity , String> {
//Trocar
//    boolean existsByOriginal_Url(String original_url);

}
