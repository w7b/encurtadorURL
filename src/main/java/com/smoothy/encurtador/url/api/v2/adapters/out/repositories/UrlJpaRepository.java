package com.smoothy.encurtador.url.api.v2.adapters.out.repositories;

import com.smoothy.encurtador.url.api.v2.adapters.out.entities.UrlEntity;
import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlJpaRepository extends JpaRepository<UrlEntity, String> {

        UrlEntity save(UrlCore urlCore);

        Optional<UrlEntity>findByHash(String hash);
        Optional<UrlEntity>findById(Long id);

        Boolean existsByIdAndHash(Long id, String hash);

        List<UrlEntity>findByExpireAtBetween(LocalDateTime createAt,
                                             LocalDateTime expireAt);

        @Transactional
        void deleteById (Long id);


}
