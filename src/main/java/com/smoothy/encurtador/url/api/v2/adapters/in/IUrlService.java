package com.smoothy.encurtador.url.api.v2.adapters.in;

import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import org.springframework.stereotype.Repository;

@Repository
public interface IUrlService {
    UrlCore generateShorterUrl(UrlCore core);
    UrlCore rUrlReceivedByHash(String hash);
    void incrementClick(String hash);
}
