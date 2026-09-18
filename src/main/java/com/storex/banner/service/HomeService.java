package com.storex.banner.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private static final Logger log = LoggerFactory.getLogger(HomeService.class);

    @CircuitBreaker(name = "bannerService", fallbackMethod = "getDefaultBannerFallback")
    public String getBanner() {
        // Logic gọi sang Banner-Service thực tế
        // ... (code rút gọn gọi RestTemplate / WebClient)
        throw new RuntimeException("Banner-Service is down!");
    }

    public String getDefaultBannerFallback(Throwable t) {
        log.error("Loi xay ra khi goi Banner-Service. Nguyen nhan: {}. Dang hien thi banner mac dinh.", t.getClass().getName(), t);
        return "Freeship mọi đơn hàng";
    }
}