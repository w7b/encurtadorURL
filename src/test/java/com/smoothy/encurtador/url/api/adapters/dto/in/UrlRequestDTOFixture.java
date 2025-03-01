package com.smoothy.encurtador.url.api.adapters.dto.in;
import com.smoothy.encurtador.url.api.v2.adapters.in.dto.in.UrlRequestDTO;

public class UrlRequestDTOFixture {
    public static UrlRequestDTO build( String url_received ){

        return new UrlRequestDTO(url_received);

    }
}
