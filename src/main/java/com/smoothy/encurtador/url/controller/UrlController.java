package com.smoothy.encurtador.url.controller;


import com.smoothy.encurtador.url.business.UrlService;
import com.smoothy.encurtador.url.business.dto.UrlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encurtador")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlDto> salvaUrl(@RequestParam("uro") String url) {
        return ResponseEntity.ok(urlService.saveAndConvertUrl(url));
    }
}
