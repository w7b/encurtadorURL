package com.smoothy.encurtador.url.api.v2.core.domain;

import java.time.LocalDateTime;

public class UrlCore {
    private Long id;
    private String url_received;
    private String hash;
    private Long clickCount;
    private LocalDateTime createAt;
    private LocalDateTime expireAt;

    public UrlCore(Long id, String url_received, String hash, Long clickCount, LocalDateTime createAt, LocalDateTime expireAt) {
        this.id = id;
        this.url_received = url_received;
        this.hash = hash;
        this.clickCount = clickCount;
        this.createAt = createAt;
        this.expireAt = expireAt;
    }

    public UrlCore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl_received() {
        return url_received;
    }

    public void setUrl_received(String url_received) {
        this.url_received = url_received;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getClickCount() {
        return (clickCount != null) ? clickCount : 0L;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}
