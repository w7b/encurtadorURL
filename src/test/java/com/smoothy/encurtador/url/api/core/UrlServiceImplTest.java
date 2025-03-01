package com.smoothy.encurtador.url.api.core;

import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import com.smoothy.encurtador.url.api.v2.core.exception.BusinessException;
import com.smoothy.encurtador.url.api.v2.core.exception.ResourceNotFoundException;
import com.smoothy.encurtador.url.api.v2.core.porters.outbound.UrlRepositoryPort;
import com.smoothy.encurtador.url.api.v2.core.services.UrlServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceImplTest {

    @InjectMocks
    private UrlServiceImpl urlService;

    @Mock
    private UrlRepositoryPort urlRepo;

    private UrlCore urlCore;

    private LocalDateTime dateCreated;
    private LocalDateTime dateExpired;

    @BeforeEach
    void setUp() {

        dateCreated = LocalDateTime.of(2025, 02, 25, 15, 00, 00);
        dateExpired = LocalDateTime.of(2025, 02, 25, 15, 30, 00);

        urlCore = new UrlCore();
        urlCore.setId(5L);
        urlCore.setUrl_received("https://github.com/");
        urlCore.setHash("b0137e7");
        urlCore.setClickCount(0L);
        urlCore.setCreateAt(dateCreated);
        urlCore.setExpireAt(dateExpired);
    }

    @Test
    void serviceHandleGenAndSaveHashTest() {
        when(urlRepo.save(any(UrlCore.class))).thenAnswer(invocations -> invocations.getArgument(0));
        UrlCore savedCore = urlService.generateShorterUrl(urlCore);
        Assertions.assertEquals("https://github.com/", savedCore.getUrl_received());
        Assertions.assertNotNull(savedCore.getHash());
        Assertions.assertFalse(savedCore.getHash().isEmpty());
        verify(urlRepo).save(any(UrlCore.class));
    }
    @Test
    void serviceNotHandleSaveGenAndHashWhenNullTest() {
        BusinessException e = Assertions.assertThrows(BusinessException.class,
                () -> urlService.generateShorterUrl(null));

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Erro ao gerar a hash"));
        verifyNoInteractions(urlRepo);
    }

    @Test
    void serviceHandleGenAndHashPerformanceTest() {
        int maxRequests = 100000;
        int maxTimeMillis = 5000;
        Long startTime = System.nanoTime();
        Instant iStart = Instant.now();

        IntStream.range(0, maxRequests).parallel().forEach(i -> {
            urlCore.setUrl_received("https://github.com/" +i);
            urlCore.getUrl_received();
            urlService.generateShorterUrl(urlCore);
        });
        Long endTime = System.nanoTime();
        Instant iEnd = Instant.now();
        System.out.println("Tempo de execução: "+(endTime - startTime) +" ns");

        Long durationMillis = Duration.between(iStart, iEnd).toMillis();
        System.out.println("O tempo foi de: " + durationMillis + " ms");

        // Verifica se a operação foi rápida o suficiente
        Assertions.assertTrue(durationMillis <= maxTimeMillis,
                "O tempo de execução foi muito alto: " + durationMillis + "ms");
    }

    @Test
    void serviceHandleReturnOriginalUrlByHash() {

        when(urlRepo.findByHash(urlCore.getHash())).thenReturn(Optional.of(urlCore));

        when(urlRepo.existsByIdAndHash(urlCore.getId(), urlCore.getHash())).thenReturn(true);
        //when(urlService.isHashLinkedToId(urlCore.getId(), urlCore.getHash())).thenReturn(true);
        urlService.rUrlReceivedByHash(urlCore.getHash());
        verify(urlRepo, times(1)).findByHash(urlCore.getHash());
        verify(urlRepo, times(1)).existsByIdAndHash(urlCore.getId(), urlCore.getHash());
        verifyNoMoreInteractions(urlRepo);
    }

    @Test
    void serviceHandleReturnOriginalUrlByHashWhenNullTest() {
        ResourceNotFoundException e = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> urlService.rUrlReceivedByHash(null));

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Nao foi possivel retornar a hash"));
    }

    @Test
    void incrementClick(){
        Long i = 1L;
        System.out.println("Antes: " + urlCore.getClickCount());
        when(urlRepo.findByHash(urlCore.getHash())).thenReturn(Optional.of(urlCore));
        //when(urlService.isHashLinkedToId(urlCore.getId(), urlCore.getHash())).thenReturn(true);

        when(urlRepo.save(urlCore)).thenReturn(urlCore);

        urlService.incrementClick(urlCore.getHash());

        verify(urlRepo, times(1)).save(any(UrlCore.class));
        Assertions.assertEquals(1L, urlCore.getClickCount());
        System.out.println("Depois: " + urlCore.getClickCount());
    }


}
