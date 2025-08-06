package org.data.nowgnas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheWarmer implements ApplicationRunner {

    private final DataService dataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Starting cache warming...");

        // 미리 캐싱해둘 데이터의 키 목록
        // 실제로는 설정 파일이나 DB에서 가져올 수 있습니다.
        List<String> popularIds = Arrays.asList("1", "2", "3", "special-data");

        for (String id : popularIds) {
            try {
                log.info("Warming up cache for id: {}", id);
                dataService.getCachedData(id);
            } catch (Exception e) {
                log.error("Failed to warm up cache for id: {}", id, e);
            }
        }

        log.info("Cache warming finished.");
    }
}
