package com.smoothy.encurtador.url.infrastructure.entity;

import com.smoothy.encurtador.url.api.v1.enums.UrlStats;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder

@Document(collection = "urls_col")
public class UrlEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String uuid;

    @Field(name = "url")
    private String OUrl;

    @Indexed(unique = true)
    private String hash;

    @Field(name = "is_available")
    @Builder.Default
    private Boolean isAvailable = true;

    @Field(name = "click_count")
    @Builder.Default
    private Long clickCount = 0L;

    @Field(name = "url_stats")
    private UrlStats urlStats;

    @Field(name = "create_at")
    private LocalDateTime createAt;

    @Indexed(expireAfter = "3600")
    @Field(name = "time_expiration")
    @Builder.Default
    private LocalDateTime timeExpiration = LocalDateTime.now().plusMinutes(30);

}