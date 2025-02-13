package com.smoothy.encurtador.url.business.dto.request;

import com.smoothy.encurtador.url.business.enums.expirationTime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashesRequestDTO {
    private String hash;
    private expirationTime expirationTime;

}
