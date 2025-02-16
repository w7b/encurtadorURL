package com.smoothy.encurtador.url.api.v1.business.converter;

import com.smoothy.encurtador.url.api.v1.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.api.v1.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class UrlConverter {

    public UrlEntity paraUrl(UrlRequestDTO url){
        return UrlEntity.builder()
                .OUrl(url.getOUrl())
                .build();
    }

    public UrlDtoResponse paraUrlDto(UrlEntity dto) {
        return UrlDtoResponse.builder()
                .uuid(dto.getUuid())
                .OUrl(dto.getOUrl())
                .id(dto.getId())
                .hash(dto.getHash())
                .createAt(LocalDateTime.now())
                .timeExpiration(LocalDateTime.now().plusMinutes(30))
                .isAvailable(dto.getIsAvailable())
                .clickCount(dto.getClickCount())
                .urlStats(dto.getUrlStats())
                .build();
    }



//
//    @Mapping(target = "OUrl", source = "OUrl")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "uuid", ignore = true)
//    @Mapping(target = "hash", ignore = true)
//    @Mapping(target = "isAvailable", expression = "java(true)") // Define como true diretamente
//    @Mapping(target = "clickCount", expression = "java(0L)") // Define como 0L diretamente
//    @Mapping(target = "urlStats", ignore = true)
//    @Mapping(target = "createAt", expression = "java(Instant.now())")
//    @Mapping(target = "timeExpiration", expression = "java(Instant.now().plusSeconds(360))")
//    UrlEntity paraUrl(UrlRequestDTO dto);
//
//    @Mapping(source = "id", target = "id")
//    UrlDtoResponse paraUrlDto(UrlEntity entity);
//
//    List<UrlDtoResponse> forUrlListDTO(List<UrlEntity> entities);
}