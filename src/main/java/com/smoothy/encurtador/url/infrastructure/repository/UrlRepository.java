package com.smoothy.encurtador.url.infrastructure.repository;

import com.smoothy.encurtador.url.business.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {

        boolean existsByIsAvailable(boolean isAvailable);

        Optional<UrlEntity> findByHash(String hash);
        Optional<UrlEntity> findById(String id);

        List<UrlDtoResponse> findByTimeExpirationBetween(LocalDateTime createAt,
                                                         LocalDateTime expirationTime);

        @Transactional
        void deleteById (String id);


}
