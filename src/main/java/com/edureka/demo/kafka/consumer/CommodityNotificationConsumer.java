package com.edureka.demo.kafka.consumer;

import com.edureka.demo.kafka.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommodityNotificationConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @KafkaListener(topics = "t_commodity", groupId = "cg-notification")
    public void consumerMessage(String message){
        var commodity = objectMapper.readValue(message, Commodity.class);
        log.info("Notification logic for {}", commodity);
    }
}
