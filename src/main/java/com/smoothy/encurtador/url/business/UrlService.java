package com.smoothy.encurtador.url.business;

import com.smoothy.encurtador.url.business.converter.UrlConverter;
import com.smoothy.encurtador.url.business.dto.in.UrlRequestDTO;
import com.smoothy.encurtador.url.business.dto.out.UrlDtoResponse;
import com.smoothy.encurtador.url.business.enums.UrlStats;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import com.smoothy.encurtador.url.infrastructure.exception.ConflictException;
import com.smoothy.encurtador.url.infrastructure.exception.ResourceNotFoundException;
import com.smoothy.encurtador.url.infrastructure.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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

    private final UrlConverter urlConverter;
    private final UrlRepository urlRepo;


    public UrlDtoResponse saveAndGenHash(UrlRequestDTO dto ) {
        String url = dto.getOUrl();
        isValidUrl(url);

        UUID uuid = UUID.randomUUID();

        String urlEncoder = Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String uniqueHash = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        hashExist(true);

        UrlEntity urlEntity = urlConverter.paraUrl(dto);

        urlEntity.setUuid(uuid.toString());
        urlEntity.setHash(uniqueHash);
        urlEntity.setUrlStats(UrlStats .ALIVE);
        urlEntity.setCreatedAt(LocalDateTime.now());
        urlEntity.setTimeExpiration(LocalDateTime.now().plusWeeks(2));

        return urlConverter.paraUrlDTO(urlRepo.save(urlEntity));
    }

    public UrlDtoResponse retornaUrlOriginalPeloHash( String hash ) {
        try {
            return urlConverter.paraUrlDTO(urlRepo.findByHash(hash)
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
    public boolean isValidUrl(String url) {
        Pattern pattern = Pattern.compile("^[(https?:\\/\\/)]?([\\w.-]+)\\.([a-z]{2,6})([\\/\\w .-]*)*\\/?$");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public void hashExist(boolean isAvailable) {
        try {
            boolean existe = verificaSeHashExiste(isAvailable);
            if (existe) {
                throw new ConflictException("URL ja existe no banco de daos " + isAvailable);
            }
        } catch ( ConflictException e ) {
            throw new ConflictException("URL ja existe no banco de dados " + isAvailable);
        }
    }

    public boolean verificaSeHashExiste(boolean isAvailable) {
        return urlRepo.existsByIsAvailable(isAvailable);
    }

    public void deleteById (String id) {
        urlRepo.deleteById(id);
    }

    public List<UrlDtoResponse> buscaUrlExpiradaPeriodo(LocalDateTime create_at,
                                                        LocalDateTime timeExpiration) {
        try{
            return urlRepo.findByTimeExpirationBetween(create_at, timeExpiration);
        } catch (ConflictException e){
            throw new ConflictException("Ocorreu algum erro ao buscar as Url's " + e.getCause());
        }
    }

    public Optional<UrlEntity> buscaUrlExpiradaPorId(String id) {
        try{
            return urlRepo.findById(id);
        } catch (ConflictException e){
            throw new ConflictException("Ocorreu algum erro ao buscar as Url's " + e.getCause());
        }
    }

    public UrlDtoResponse updateStatus(UrlStats urlStats, String id){
        try{
            UrlEntity entity = urlRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Url not found"));
            entity.setUrlStats(urlStats);
            return urlConverter.paraUrlDTO(urlRepo.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Url not found " +e.getCause());
        }
    }

}