package com.smoothy.encurtador.url.api.v1.business;

import com.smoothy.encurtador.url.api.v1.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.api.v1.enums.UrlStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    @Autowired
    private final UrlService urlService;

    @Scheduled(cron = "${cron-time}")
    public void findUrlByExpiredTime() {
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime timeExpiration = LocalDateTime.now().plusMinutes(15);

        List<UrlDtoResponse> urlList = urlService.findUrlExpiradaPeriodo(createAt, timeExpiration);
        log.info("Lista de Urls encontradas " +urlList);

        urlList.forEach(url -> {
            urlService.findUrlExpiradaPorId(url.getId());
            UrlDtoResponse urlStatusUpdater = urlService.statsUpdate(UrlStats.EXPIRED, url.getId());
            log.info("URL's " +url.getOUrl()+ "has defined to " +urlStatusUpdater);
            urlService.deleteById(url.getId());
            log.info("A URL encurtada foi deletada com sucesso da nossa DataBase");
        });
        log.info("Finalizada a busca de URL's expiradas.");
    }
}
