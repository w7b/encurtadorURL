package com.smoothy.encurtador.url.api.v2.core.porters.outbound;

import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepositoryPort {

    UrlCore save (UrlCore urlCore);

    List<UrlCore> findByExpireAtBetween(LocalDateTime createAt, LocalDateTime expireAt);

    Optional<UrlCore> findById (Long id);
    Optional<UrlCore> findByHash (String hash);

    Boolean existsByIdAndHash(Long id, String hash);

    void deleteById (Long id);
}
