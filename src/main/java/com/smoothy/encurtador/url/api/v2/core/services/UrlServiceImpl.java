package com.smoothy.encurtador.url.api.v2.core.services;

import com.smoothy.encurtador.url.api.v2.adapters.in.IUrlService;
import com.smoothy.encurtador.url.api.v2.core.domain.UrlCore;
import com.smoothy.encurtador.url.api.v2.core.exception.BusinessException;
import com.smoothy.encurtador.url.api.v2.core.exception.ConflictException;
import com.smoothy.encurtador.url.api.v2.core.exception.NotNullException;
import com.smoothy.encurtador.url.api.v2.core.exception.ResourceNotFoundException;
import com.smoothy.encurtador.url.api.v2.core.porters.outbound.UrlRepositoryPort;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

public class UrlServiceImpl implements IUrlService {

    private final UrlRepositoryPort urlRepo;

    public UrlServiceImpl(UrlRepositoryPort urlRepo) {
        this.urlRepo = urlRepo;
    }

    @Override
    public UrlCore generateShorterUrl(UrlCore urlCore) { //
        if (urlCore == null){
            throw new BusinessException("Error generating hash", new NullPointerException("core is not null"));
        }

        String url = urlCore.getUrl_received();
        urlValidation(url);
        urlContainsValidation(url);
        urlLenghtCheck(url);
        urlCore.setUrl_received(url);
        generateHash(urlCore);
        urlCore.setCreateAt(LocalDateTime.now());
        return urlRepo.save(urlCore);
    }

    @Override
    public UrlCore rUrlReceivedByHash(String hash) {
        UrlCore core = urlRepo.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException("Unable to return hash")); //!!!!!!!!!

        if (!isHashLinkedToId(core.getId(), hash)){
            throw new ResourceNotFoundException("The hash isn't not linked to the id informed");
        }
        return core;
    }

    @Override
    public void incrementClick(String hash) {
        UrlCore core = urlRepo.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException("Hash not found"));

        insertClick(core);

        if (!isHashLinkedToId(core.getId(), hash)){
            throw new ResourceNotFoundException("The hash isn't not linked to the id informed");
        }
    }

    private void insertClick(UrlCore urlCore) {
        Long i = 1L;
        urlCore.setClickCount(urlCore.getClickCount() +i); //
        urlRepo.save(urlCore);
    }

    private void urlValidation(String url){
        if (url == null || url.isBlank()){
            throw new NotNullException("A url cannot null");
        }
    }
    private static Boolean urlContainsValidation(String url){
        String stringMissing = "";
        if (!url.contains("//") || !url.contains(":")){
            if (!(url.contains("//")) ){
                stringMissing = "//";
            } else {
                stringMissing = ":";
            }
            throw new NotNullException("Put a url that contains // and :");
        }
        return true;
    }
    public static Boolean urlLenghtCheck(String url){
        if(url.length() <= 20){
            throw new ResourceNotFoundException("need to 20 caracther urls");
        }
        return true;
    }

    private void generateHash(UrlCore urlCore){
        String url = urlCore.getUrl_received();
        String uniqueHash;
        do {
            Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
            uniqueHash = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        } while (isHashLinkedToId(urlCore.getId(), uniqueHash));
        urlCore.setHash(uniqueHash);
    }

    private Boolean isHashLinkedToId(Long id, String hash){ //private
        try{
            return urlRepo.existsByIdAndHash(id, hash); //!!!!!!!!!!!!!
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Boolean Equals: "+urlRepo.existsByIdAndHash(id,hash));
        }
    }

    //To Cron Service
    protected Optional<UrlCore> searchUrlsId(Long id) {
        try{
            return urlRepo.findById(id);
        } catch (ConflictException e){
            throw new ConflictException("An error occurred while fetching the URLs " + e.getCause());
        }
    }

    protected void deleteById (Long id) {

        urlRepo.deleteById(id);
    }

    protected List<UrlCore> searchUrlsInPeriod(LocalDateTime createAt,
                                               LocalDateTime expireAt) {
        try{
            return urlRepo.findByExpireAtBetween(createAt, expireAt);
        } catch (ConflictException e){
            throw new ConflictException("Ocorreu algum erro ao buscar as Url's " + e.getCause());
        }
    }
}