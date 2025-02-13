package com.smoothy.encurtador.url.business.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDtoResponse {

    private String id;
    private String uuid;
    private String original_url;
    private String hash;
    private LocalDateTime create_at;

}
