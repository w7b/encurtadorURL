package com.smoothy.encurtador.url.business.converter;

import com.smoothy.encurtador.url.business.dto.request.HashesRequestDTO;
import com.smoothy.encurtador.url.business.dto.request.UrlRequestDTO;
import com.smoothy.encurtador.url.business.dto.response.HashesDTOResponse;
import com.smoothy.encurtador.url.business.dto.response.UrlDtoResponse;
import com.smoothy.encurtador.url.infrastructure.entity.HashsEntity;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlConverter {

    public UrlEntity paraUrl (UrlRequestDTO url){
        return UrlEntity.builder()
                .original_url(url.getOriginal_url())
                .build();
    }

    public UrlDtoResponse paraUrlDTO (UrlEntity dto) {
        return UrlDtoResponse.builder()
                .uuid(dto.getUuid())
                .original_url(dto.getOriginal_url())
                .id(dto.getId())
                .hash(dto.getHash())
                .create_at(LocalDateTime.now())
                .build();
    }

    public HashsEntity paraHash (HashesRequestDTO hashdto) {
        return HashsEntity.builder()
                .hash(hashdto.getHash())
                .timeExpiration(hashdto.getExpirationTime())
                .build();
    }

    public HashesDTOResponse paraHashDTO (HashsEntity dto) {
        return HashesDTOResponse.builder()
                .hash(dto.getHash())
                .isAvailable(dto.isAvailable())
                .expirationTime(dto.getTimeExpiration())
                .build();
    }

}
