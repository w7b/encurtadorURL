package com.smoothy.encurtador.url.controller;

import com.smoothy.encurtador.url.business.UrlService;
import com.smoothy.encurtador.url.business.dto.request.HashesRequestDTO;
import com.smoothy.encurtador.url.business.dto.request.UrlRequestDTO;
import com.smoothy.encurtador.url.business.dto.response.UrlDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encurtador")
@RequiredArgsConstructor

public class UrlController {

    @Autowired
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlDtoResponse> salvaUrl(@RequestBody UrlRequestDTO urlDTO){
        UrlDtoResponse savedUrl = urlService.saveUrlOriginal(urlDTO);
//        UrlDtoResponse hashedUrl = urlService.generateHash(urlDTO, hashDTO.getHash());
        return ResponseEntity.ok(savedUrl);
    }

}
