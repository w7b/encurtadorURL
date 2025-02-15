package com.smoothy.encurtador.url.business.converter;

import com.smoothy.encurtador.url.business.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.business.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlConverter {

    public UrlEntity paraUrl (UrlRequestDTO url){
        return UrlEntity.builder()
                .OUrl(url.getOUrl())
                .build();
    }

    public UrlDtoResponse paraUrlDTO (UrlEntity dto) {
        return UrlDtoResponse.builder()
                .uuid(dto.getUuid())
                .OUrl(dto.getOUrl())
                .id(dto.getId())
                .hash(dto.getHash())
                .createAt(LocalDateTime.now())
                .timeExpiration(LocalDateTime.now())
                .isAvailable(dto.isAvailable())
                .clickCount(dto.getClickCount())
                .urlStats(dto.getUrlStats())
                .build();
    }
}
