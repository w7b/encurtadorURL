package com.smoothy.encurtador.url.api.v2.infra.config;

import com.smoothy.encurtador.url.api.v2.core.porters.outbound.UrlRepositoryPort;
import com.smoothy.encurtador.url.api.v2.core.services.CronService;
import com.smoothy.encurtador.url.api.v2.core.services.UrlServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class BeanConfigs {

    @Bean
    public UrlServiceImpl urlService (UrlRepositoryPort urlRepositoryPort){
        return new UrlServiceImpl(urlRepositoryPort);
    }

    @Bean
    public CronService cronService(UrlServiceImpl urlServiceImpl){
        return new CronService(urlServiceImpl);
    }

    @Bean
    public Clock clock (){
        return Clock.systemDefaultZone();
    }
}
