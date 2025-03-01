package com.smoothy.encurtador.url.api.v2.adapters.in.controller;

import com.smoothy.encurtador.url.api.v2.adapters.in.IUrlService;
import com.smoothy.encurtador.url.api.v2.adapters.in.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.api.v2.adapters.in.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.api.v2.adapters.mapper.UrlMapper;
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
    private final IUrlService iUrlService;
    private final UrlMapper mapper;

    @PostMapping("/urls")
    public ResponseEntity<UrlDtoResponse> saveAndConvert(@RequestBody UrlRequestDTO urlDTO){
        UrlDtoResponse salvingUrl = mapper.dtoToCore(iUrlService.generateShorterUrl(mapper.forCore(urlDTO)));
        return ResponseEntity.ok(salvingUrl);
    }

    @GetMapping("/urls/{hash}")
    public ResponseEntity<Void> retornaUrlReceived(@PathVariable String hash){
        iUrlService.incrementClick(hash);
        String urlReceived = iUrlService.rUrlReceivedByHash(hash).getUrl_received();

        if (urlReceived == null || urlReceived.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", urlReceived);

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}