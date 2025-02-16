package com.smoothy.encurtador.url.api.v1.business.converter;

import com.smoothy.encurtador.url.api.v1.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UrlUpdateConverter {
    void updateUrl(UrlDtoResponse dto, @MappingTarget UrlEntity entity);
}

