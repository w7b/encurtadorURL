package com.smoothy.encurtador.url.api.v2.adapters.out.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "urls_table")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url_received;

    @Column(name = "hash" , length = 7, nullable = false, unique = true)
    private String hash;

    @Column(name = "click_count", nullable = false)
    @Builder.Default
    private Long clickCount = 0L;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "expire_at", nullable = false)
    private LocalDateTime expireAt;
}