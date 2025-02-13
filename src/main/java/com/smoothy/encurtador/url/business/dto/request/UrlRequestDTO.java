package com.smoothy.encurtador.url.business.dto.request;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlRequestDTO {

    private String original_url;
}
