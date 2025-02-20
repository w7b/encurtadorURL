package com.smoothy.encurtador.url.infrastructure.entity;

import com.smoothy.encurtador.url.business.enums.UrlStats;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document("urls_col")
public class UrlEntity {

    @Id
    private String id;
    private String uuid;
    private String OUrl;
    private String hash;

    @Field(name = "create_at")
    private LocalDateTime createAt;
    @Field(name = "time_expiration")
    private LocalDateTime timeExpiration;

    @Field(name = "is_available")
    private boolean isAvailable;

    @Builder.Default
    @Field(name = "click_count")
    private Long clickCount = 0L;

    @Field(name = "url_stats")
    private UrlStats urlStats;


    public void setCreatedAt(LocalDateTime now) {
    }
}