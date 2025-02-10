package com.smoothy.encurtador.url.business.converter;

import com.smoothy.encurtador.url.business.dto.UrlDto;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.stereotype.Component;

@Component
public class UrlConverter {

    public UrlEntity paraUrl (UrlDto url){
        return UrlEntity.builder()
                .uro(url.getUro())
                .ure(url.getUre())
                .build();
    }

    public UrlDto paraUrlDTO (UrlDto url) {
        return UrlDto.builder()
                .uro(url.getUro())
                .ure(url.getUre())
                .build();
    }

}
