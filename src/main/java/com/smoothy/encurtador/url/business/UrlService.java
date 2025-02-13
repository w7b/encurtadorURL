package com.smoothy.encurtador.url.business;

import com.smoothy.encurtador.url.business.converter.UrlConverter;
import com.smoothy.encurtador.url.business.dto.request.UrlRequestDTO;
import com.smoothy.encurtador.url.business.dto.response.HashesDTOResponse;
import com.smoothy.encurtador.url.business.dto.response.UrlDtoResponse;
import com.smoothy.encurtador.url.business.enums.expirationTime;
import com.smoothy.encurtador.url.infrastructure.entity.UrlEntity;
import com.smoothy.encurtador.url.infrastructure.exceptions.ConflictException;
import com.smoothy.encurtador.url.infrastructure.exceptions.ResourceNotFoundException;
import com.smoothy.encurtador.url.infrastructure.repository.HashRepository;
import com.smoothy.encurtador.url.infrastructure.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j

public class UrlService {

    private final UrlConverter urlConverter;
    private final UrlRepository urlRepo;
    private final HashRepository hashRepo;


    public UrlDtoResponse saveUrlOriginal (UrlRequestDTO dto ) {
        isValidUrl(dto.getOriginal_url());
        String url = dto.getOriginal_url();

        UUID uuid = UUID.randomUUID();

        String urlEncoder = Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        String uniqueHash = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        hashExiste(true);

        UrlEntity urlEntity = urlConverter.paraUrl(dto);

        urlEntity.setUuid(uuid.toString());
        urlEntity.setHash(uniqueHash);
        urlEntity.setCreatedAt(LocalDateTime.now());

        return urlConverter.paraUrlDTO(urlRepo.save(urlEntity));
    }

    public boolean isValidUrl(String url) {
        String regex = "^(https?|ftp)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}.*$"; // Estudar isso e fazer do 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    private static String ajustarTamanho(String hash) {
        StringBuilder sb = new StringBuilder(hash);
        while (sb.length() < 7) {
            sb.append("X"); // Preenche com 'X' caso a string fique muito curta
        }
        return sb.substring(0, 7);
    }

    public void hashExiste(boolean isAvailable) {
        try {
            boolean existe = verificaSeHashExiste(isAvailable = false);
            if (existe) {
                throw new ConflictException("URL ja existe no banco de daos " + isAvailable);
            }
        } catch ( ConflictException e ) {
            throw new ConflictException("URL ja existe no banco de dados " + isAvailable);
        }
    }

    public boolean verificaSeHashExiste(boolean isAvailable) {
        //Fazer uma logica
        return hashRepo.existsByIsAvailable(isAvailable);
    }

    public void deleteByUrl(HashesDTOResponse expiredHash) {
        hashRepo.deleteBytimeExpiration(expirationTime.NULO);
    } // Refazer

    public List<HashesDTOResponse> buscaHashsExpiradas () {
        try{
            return buscaHashsExpiradas();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Algo nao ocorreu como o esperado");
        }
    }


}