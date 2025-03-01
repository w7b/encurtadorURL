package com.smoothy.encurtador.url.api.v2.adapters.in.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequestDTO {
    private String url_received;
}
