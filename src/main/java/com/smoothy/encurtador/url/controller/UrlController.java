package com.smoothy.encurtador.url.controller;

import com.smoothy.encurtador.url.business.UrlService;
import com.smoothy.encurtador.url.business.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.business.dto.out.UrlDtoResponse;
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
        UrlDtoResponse savedUrl = urlService.saveAndGenHash(urlDTO);
        return ResponseEntity.ok(savedUrl);
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
