package com.smoothy.encurtador.url.api.v1.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smoothy.encurtador.url.api.v1.enums.UrlStats;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createAt;
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeExpiration = LocalDateTime.now().plusMinutes(30);

    private Boolean isAvailable;

    private Long clickCount;

    private UrlStats urlStats;
}
