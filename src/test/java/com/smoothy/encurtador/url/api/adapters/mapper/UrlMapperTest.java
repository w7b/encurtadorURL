package com.smoothy.encurtador.url.api.adapters.mapper;

import com.smoothy.encurtador.url.api.adapters.dto.out.UrlDtoResponseFixture;
import com.smoothy.encurtador.url.api.v2.adapters.in.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.api.v2.adapters.mapper.UrlMapper;
import com.smoothy.encurtador.url.api.v2.adapters.out.entities.UrlEntity;
import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import com.smoothy.encurtador.url.api.v2.core.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlMapperTest{

    @Mock
    UrlMapper urlMapper = UrlMapper.INSTANCE;
    UrlEntity urlEntity;
    UrlCore urlCore;
    UrlDtoResponse response;

    LocalDateTime dateCreated;
    LocalDateTime dateExpired;


    @BeforeEach
    public void setUp() {
        UrlMapper urlMapper = Mappers.getMapper(UrlMapper.class);

        dateCreated = LocalDateTime.of(2025, 02, 25, 15, 00, 00);
        dateExpired = LocalDateTime.of(2025, 02, 25, 15, 30, 00);

        urlEntity = UrlEntity.builder()
                .id(5L).url_received("https://github.com/").hash("b0137e7")
                .clickCount(0L).createAt(dateCreated).expireAt(dateExpired)
                .build();
        response = UrlDtoResponseFixture.build(
                5L, "https://github.com/", "b0137e7",
                0L, dateCreated, dateExpired);

    }

    @Test
    void mapperShouldHandleEntityEDtoEquals() {
        urlCore = urlMapper.toCore(urlEntity);
        UrlDtoResponse dtoResponse = urlMapper.dtoToCore(urlCore);

        Assertions.assertEquals(response.getId(), urlEntity.getId());
        Assertions.assertEquals(response.getUrl_received(), urlEntity.getUrl_received());
        Assertions.assertEquals(response.getHash(), urlEntity.getHash());
        Assertions.assertEquals(response.getClickCount(), urlEntity.getClickCount());
        Assertions.assertEquals(response.getCreateAt(), urlEntity.getCreateAt());
        Assertions.assertEquals(response.getExpireAt(), urlEntity.getExpireAt());

    }

//    @Test
//    void mapperShouldHandleNullFields(){
//        BusinessException e = Assertions.assertThrows(BusinessException.class,
//                () -> doReturn(null));
//
//        assertThat(e, notNullValue());
//        assertThat(e.getMessage(), is("Erro ao gravar um usuario recebidos apenas parametros nulos"));
//        assertThat(e, notNullValue());
//        assertThat(e.getCause().getMessage(), is("Os dados do usuário são obrigatórios"));
//        verifyNoInteractions(urlCore);
//    }
}