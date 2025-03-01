package com.smoothy.encurtador.url.api.adapters.dto.out;

import com.smoothy.encurtador.url.api.v2.adapters.in.dto.out.UrlDtoResponse;

import java.time.LocalDateTime;

public class UrlDtoResponseFixture {

    public static UrlDtoResponse build( Long id,
                                        String url_received,
                                        String hash,
                                        Long clickCount,
                                        LocalDateTime createAt,
                                        LocalDateTime expireAt ){
        return new UrlDtoResponse(id, url_received, hash, clickCount, createAt, expireAt);
    }
}
