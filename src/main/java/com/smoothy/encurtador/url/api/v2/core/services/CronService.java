package com.smoothy.encurtador.url.api.v2.core.services;

import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@ToString
public class CronService {

    private final UrlServiceImpl urlServiceImpl;
    private static final Logger LOGGER = LoggerFactory.getLogger(CronService.class);

    public CronService(UrlServiceImpl urlServiceImpl) {
        this.urlServiceImpl = urlServiceImpl;
    }

    @Scheduled(cron = "${cron.times.handleFifteenMin}")
    public void findUrlByExpiredTime() {
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime expireAt  = LocalDateTime.now().plusMinutes(30);

        List<UrlCore> allUrlListExpiring = urlServiceImpl.searchUrlsInPeriod(createAt, expireAt);
        LOGGER.info("List of found urls: " + allUrlListExpiring.toString());

        allUrlListExpiring.forEach(url -> {
            urlServiceImpl.searchUrlsId(url.getId());
            LOGGER.info("URL's " +url.getUrl_received().toString()+ " has found "); // Trocar a mensagem dos loggers
            urlServiceImpl.deleteById(url.getId());
            LOGGER.info("The shortened URL " +url.getHash().toString()+ " has been successfully deleted from our Database");
        }); // Refazer
        LOGGER.info("Finished searching for expired URLs.");
    }
}
