package com.smoothy.encurtador.url.infrastructure.entity;

import com.smoothy.encurtador.url.business.enums.expirationTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder

@Document("hashes_table")
public class HashsEntity {

    @Id
    private String hash;
    @Field("is_available")
    private boolean isAvailable;
    private expirationTime timeExpiration;

}
