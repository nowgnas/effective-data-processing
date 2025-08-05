package org.data.nowgnas.infrastructure;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.data.nowgnas.aop.InterfaceObserver;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSyncClient {

    private final WebClient webClient;

    @InterfaceObserver
    public void sync(String message, Object value) {
        if (!Objects.equals(message, "hello")) {
            throw new IllegalArgumentException("Invalid message: " + message);
        }
        webClient.post()
                .uri("/data/sync")
                .bodyValue(value)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> {
                    log.info("Consumed message and called /data/sync: {}", message);
                })
                .doOnError(error -> log.error("Failed to process message: {}", message, error))
                .block(); // block() to make it synchronous and propagate exceptions
    }
}
