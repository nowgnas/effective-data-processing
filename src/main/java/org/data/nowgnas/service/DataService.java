package org.data.nowgnas.service;

import lombok.extern.slf4j.Slf4j;
import org.data.nowgnas.dto.CachedData;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataService {

    @Cacheable(value = "cachedData", key = "#id")
    public CachedData getCachedData(String id) {
        log.info("Cache MISS for id: {}. Fetching data...", id);
        // This is a dummy implementation to simulate data fetching.
        List<String> stringList = Arrays.asList("a", "b", "c");
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("key1", "value1");
        objectMap.put("key2", 123);
        return new CachedData(stringList, objectMap);
    }
}
