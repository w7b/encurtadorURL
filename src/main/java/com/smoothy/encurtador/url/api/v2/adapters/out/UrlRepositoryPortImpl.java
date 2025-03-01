package com.smoothy.encurtador.url.api.v2.adapters.out;

import com.smoothy.encurtador.url.api.v2.adapters.mapper.UrlMapper;
import com.smoothy.encurtador.url.api.v2.adapters.out.repositories.UrlJpaRepository;
import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import com.smoothy.encurtador.url.api.v2.core.porters.outbound.UrlRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class UrlRepositoryPortImpl implements UrlRepositoryPort {

    private final UrlJpaRepository urlJpaRepository;
    private final UrlMapper urlMapper;

    @Override
    public UrlCore save(UrlCore urlCore) {
        return urlMapper.toCore(
                urlJpaRepository.save(
                        urlMapper.toEntity(urlCore)));
    }

    @Override
    public List<UrlCore> findByExpireAtBetween(LocalDateTime createAt, LocalDateTime expireAt) {
        return urlJpaRepository.findByExpireAtBetween(createAt, expireAt)
                .stream()
                .map(urlMapper::toCore)
                .toList();
    }

    @Override
    public Optional<UrlCore> findById(Long id) {
        return urlJpaRepository.findById(id).map(urlMapper::toCore);
    }

    @Override
    public Optional<UrlCore> findByHash(String hash) {
        return urlJpaRepository.findByHash(hash).map(urlMapper::toCore);
    }

    @Override
    public Boolean existsByIdAndHash(Long id, String hash) {
        return urlJpaRepository.existsByIdAndHash(id, hash);
    }

    @Override
    public void deleteById(Long id) {
        urlJpaRepository.deleteById(id);
    }
}
