package com.smoothy.encurtador.url.api.v1.business;

import com.smoothy.encurtador.url.api.v1.business.converter.UrlConverter;
import com.smoothy.encurtador.url.api.v1.business.converter.UrlUpdateConverter;
import com.smoothy.encurtador.url.api.v1.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.api.v1.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.api.v1.enums.UrlStats;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import com.smoothy.encurtador.url.infrastructure.exception.ConflictException;
import com.smoothy.encurtador.url.infrastructure.exception.ResourceNotFoundException;
import com.smoothy.encurtador.url.infrastructure.exception.UrlNotFoundException;
import com.smoothy.encurtador.url.infrastructure.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RequiredArgsConstructor
@Service
@Slf4j

public class UrlService {

    @Autowired
    private final UrlConverter urlConverter;
    private final UrlRepository urlRepo;
    private final UrlUpdateConverter urlUpdateConverter;

    public UrlDtoResponse saveAndGenHash( UrlRequestDTO dto ) {
        String url = dto.getOUrl();
        isValidUrl(url);

        UUID uuid = UUID.randomUUID();

        String urlEncoder = Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String uniqueHash = UUID.randomUUID().toString().replace("-", "").substring(0, 7);

        UrlEntity urlEntity = urlConverter.paraUrl(dto);

        urlEntity.setUuid(uuid.toString());
        urlEntity.setHash(uniqueHash);
        urlEntity.setCreateAt(LocalDateTime.now());
        urlEntity.setUrlStats(UrlStats.ALIVE);
        urlEntity.setIsAvailable(false);

        return urlConverter.paraUrlDto(urlRepo.save(urlEntity));
    }

    public UrlDtoResponse retornaUrlOriginalPeloHash( String hash ) {
        try {
            return urlConverter.paraUrlDto(urlRepo.findByHash(hash)
                    .orElseThrow(() ->
            new ResourceNotFoundException("Nao foi possivel verificar as informacoes da hash: " + hash)));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Nao foi possivel verificar as informacoes da hash: " + hash);
        }
    }

    public void incrementarClick(String hash) {
        UrlEntity entity = urlRepo.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException("Hash não encontrada"));
        entity.setClickCount((entity.getClickCount() != null ? entity.getClickCount() : 0) +1);
        urlRepo.save(entity);
    }

    private void isValidUrl(String url) {
        if (url == null || url.isEmpty()){
            throw new UrlNotFoundException("URL not found!");
        }

        String regex = "^(https?:\\/\\/)?([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})(\\/[-a-zA-Z0-9@:%._+~#=]*)*(\\?[;&a-zA-Z0-9%._+~#=-]*)?(#[a-zA-Z0-9-_]*)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        log.info("Validando URL: " + url);
        if (!matcher.matches()) {
            throw new UrlNotFoundException("Invalid URL format!");
        }
        log.info("Resultado da validação: " + matcher.matches());
    }

//    private void hashExist(boolean isAvailable) {
//        try {
//            boolean existe = hashVerifyExist(isAvailable);
//            if (existe) {
//                throw new ConflictException("" + isAvailable);
//            }
//        } catch ( ConflictException e ) {
//            throw new ConflictException("URL ja existe no banco de dados " + isAvailable);
//        }
//    }
//    private boolean hashVerifyExist(boolean isAvailable) {
//        return urlRepo.existsByIsAvailable(isAvailable);
//    }

    protected void deleteById (String id) {
        urlRepo.deleteById(id);
    }

    protected List<UrlDtoResponse> findUrlExpiradaPeriodo(LocalDateTime create_at,
                                                          LocalDateTime timeExpiration) {
        try{
            return urlRepo.findByTimeExpirationBetween(create_at, timeExpiration);
        } catch (ConflictException e){
            throw new ConflictException("Ocorreu algum erro ao buscar as Url's " + e.getCause());
        }
    }

    protected Optional<UrlEntity> findUrlExpiradaPorId(String id) {
        try{
            return urlRepo.findById(id);
        } catch (ConflictException e){
            throw new ConflictException("Ocorreu algum erro ao buscar as Url's " + e.getCause());
        }
    }

    protected UrlDtoResponse statsUpdate(UrlStats urlStats, String id){
        try {
            return alternateState(urlStats , id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Stats not update " +e.getCause());
        }
    }

    protected UrlDtoResponse alternateState(UrlStats urlStats, String id){
        try{
            UrlEntity entity = urlRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Url not found"));
            UrlDtoResponse dto = new UrlDtoResponse();
            dto.setUrlStats(urlStats);
            urlUpdateConverter.updateUrl(dto, entity);
            return urlConverter.paraUrlDto(entity);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Url not found " +e.getCause());
        }
    }

}