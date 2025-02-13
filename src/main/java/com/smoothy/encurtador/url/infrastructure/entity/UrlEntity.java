package com.smoothy.encurtador.url.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document("urls_table")
public class UrlEntity {

    @Id
    private String id;
    private String uuid;
    private String original_url;
    private String hash;
    private LocalDateTime create_at;

    public void setCreatedAt(LocalDateTime now) {

    }
}