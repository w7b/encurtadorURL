package com.smoothy.encurtador.url.business;

import com.smoothy.encurtador.url.business.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.business.enums.UrlStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final UrlService urlService;

    @Scheduled(cron = "${cron.hour}")
    public void findUrlByExpiredTime() {
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime timeExpiration = LocalDateTime.now().plusWeeks(2);

        List<UrlDtoResponse> urlList = urlService.buscaUrlExpiradaPeriodo(createAt, timeExpiration);
        log.info("Lista de Urls encontradas " +urlList);

        urlList.forEach(url -> {
            urlService.buscaUrlExpiradaPorId(url.getId());
            UrlDtoResponse urlStatusUpdater = urlService.updateStatus(UrlStats.EXPIRED, url.getId());
            log.info("URL's " +url.getOUrl()+ "has defined to " +urlStatusUpdater);
            urlService.deleteById(url.getId());
            log.info("A URL encurtada foi deletada com sucesso da nossa DataBase");
        });
        log.info("Finalizada a busca de URL's expiradas.");
    }
}
