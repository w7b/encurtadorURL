package com.smoothy.encurtador.url.business.dto.out;

import com.smoothy.encurtador.url.business.enums.UrlStats;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDtoResponse {

    @Id
    private String id;
    private String uuid;
    private String OUrl;
    private String hash;

    private LocalDateTime createAt;
    private LocalDateTime timeExpiration;

    private boolean isAvailable;

    @Builder.Default
    private Long clickCount = 0L;

    private UrlStats urlStats;
}
