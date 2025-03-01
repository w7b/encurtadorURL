package com.smoothy.encurtador.url.api.v2.adapters.mapper;

import com.smoothy.encurtador.url.api.v2.adapters.in.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.api.v2.adapters.in.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.api.v2.adapters.out.entities.UrlEntity;
import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {

    UrlMapper INSTANCE = Mappers.getMapper(UrlMapper.class);

    UrlEntity toEntity(UrlCore urlCore);
    UrlCore toCore(UrlEntity urlEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "url_received", target = "url_received") // Exemplo de conversão
    @Mapping(target = "hash", ignore = true) // Se o hash for gerado depois
    @Mapping(target = "clickCount", constant = "0L") // Definir um valor fixo
    @Mapping(target = "createAt", expression = "java(java.time.LocalDateTime.now())") // Criar a data na conversão
    @Mapping(target = "expireAt", expression = "java(java.time.LocalDateTime.now().plusMinutes(30))") // Expiração padrão
    UrlCore forCore (UrlRequestDTO url);

    UrlDtoResponse dtoToCore(UrlCore url);

}
