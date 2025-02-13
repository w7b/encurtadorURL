package com.smoothy.encurtador.url.infrastructure.repository;

import com.smoothy.encurtador.url.business.enums.expirationTime;
import com.smoothy.encurtador.url.infrastructure.entity.HashsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HashRepository extends MongoRepository<HashsEntity, String> {
    boolean existsByIsAvailable(boolean isAvailable);

    @Transactional
    void deleteBytimeExpiration(expirationTime expirationTime );


}
