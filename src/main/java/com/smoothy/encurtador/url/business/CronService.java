package com.smoothy.encurtador.url.business;

import com.smoothy.encurtador.url.business.dto.response.HashesDTOResponse;
import com.smoothy.encurtador.url.business.enums.expirationTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CronService {

    private final UrlService urlService;

    @Scheduled(cron = "${cron.time}")
    public void deleteUrlsInExpirationTime () {
        log.info("Iniciando a busca por URLS no tempo de expiracao");

        List<HashesDTOResponse> listaDeHashs = urlService.buscaHashsExpiradas();

        listaDeHashs.forEach( expiredHash ->{
            urlService.deleteByUrl(expiredHash);
            log.info("A tarefa com " +expiredHash+ "Foi deletada com sucesso");
        });
    }
}
