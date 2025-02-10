package com.smoothy.encurtador.url.business;

import com.smoothy.encurtador.url.business.converter.UrlConverter;
import com.smoothy.encurtador.url.business.dto.UrlDto;
import com.smoothy.encurtador.url.infrastructure.ResourceNotFoundException;
import com.smoothy.encurtador.url.infrastructure.repository.UrlRepository;
import io.seruco.encoding.base62.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlDto urlDto;
    private final UrlConverter urlConverter;
    private final UrlRepository urlRepository;


    Base62 base62 = Base62.createInstance();

    public UrlDto saveAndConvertUrl(String url) {
        try{
            urlDto.setUro(url);
            urlConverter.paraUrlDTO(urlDto);


            final byte[] encoded = base62.encode(url.getBytes());
            new String(encoded);
            return urlConverter.paraUrlDTO(urlDto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(("Ocorreu um erro ao salvar ou encurtar a url"+ e.getCause()));
        }
    }


}