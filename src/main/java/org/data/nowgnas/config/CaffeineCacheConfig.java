package org.data.nowgnas.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Weigher;
import org.data.nowgnas.dto.CachedData;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineSpec());
        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineSpec() {
        return Caffeine.newBuilder()
                // 5분 동안 접근이 없으면 만료
                .expireAfterAccess(5, TimeUnit.MINUTES)
                // 초기 용량 설정
                .initialCapacity(100)
                // 최대 10,000의 "무게"까지 저장, 초과 시 제거
                .maximumWeight(10000)
                // 각 항목의 무게는 Weigher로 계산
                .weigher(cachedDataWeigher())
                // 통계 수집 활성화
                .recordStats();
    }

    // CachedData 객체의 대략적인 무게를 계산하는 Weigher
    // 실제로는 더 정교한 계산이 필요할 수 있습니다.
    private Weigher<Object, Object> cachedDataWeigher() {
        return (key, value) -> {
            if (value instanceof CachedData) {
                CachedData data = (CachedData) value;
                // 리스트 크기 + 맵 크기를 대략적인 무게로 산정
                return data.getStringList().size() + data.getObjectMap().size();
            } else {
                // 다른 타입의 객체는 기본 무게 1로 처리
                return 1;
            }
        };
    }
}
