package com.smoothy.encurtador.url.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

@Table(name = "urls")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uro", unique = true)
    private String uro;
    @Column(name = "ure") //Posso colocar depois um limite lenght
    private String ure;


}
