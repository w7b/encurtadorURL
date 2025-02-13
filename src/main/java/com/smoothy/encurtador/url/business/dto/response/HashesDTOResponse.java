package com.smoothy.encurtador.url.business.dto.response;

import com.smoothy.encurtador.url.business.enums.expirationTime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashesDTOResponse {
    private String hash;
    private boolean isAvailable;
    private expirationTime expirationTime;
}
