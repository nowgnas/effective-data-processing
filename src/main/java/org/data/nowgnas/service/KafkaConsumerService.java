package org.data.nowgnas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.data.nowgnas.infrastructure.DataSyncClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final DataSyncClient dataSyncClient;

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consume(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment) {
        try {
            // Call the /data/sync API
            dataSyncClient.sync(record.topic(), record.value());
        } catch (Exception e) {
            // Handle processing errors
            log.error("Failed to process message: {}", record, e);
        }finally{
            acknowledgment.acknowledge();
        }
    }
}
