package com.smoothy.encurtador.url.api.v1.controller;

import com.smoothy.encurtador.url.api.v1.business.UrlService;
import com.smoothy.encurtador.url.api.v1.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.api.v1.dto.out.UrlDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequiredArgsConstructor

public class UrlController {

    @Autowired
    private final UrlService urlService;

    @PostMapping("/urls")
    public ResponseEntity<UrlDtoResponse> salvaUrl(@RequestBody UrlRequestDTO urlDTO){
        UrlDtoResponse savingUrl = urlService.saveAndGenHash(urlDTO);
        return ResponseEntity.ok(savingUrl);
    }

    @GetMapping("/urls/{hash}")
    public ResponseEntity<Void> retornaUrlOriginal(@PathVariable String hash){
        urlService.incrementarClick(hash);
        String originalUrl = urlService.retornaUrlOriginalPeloHash(hash).getOUrl();

        if (originalUrl == null || originalUrl.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}