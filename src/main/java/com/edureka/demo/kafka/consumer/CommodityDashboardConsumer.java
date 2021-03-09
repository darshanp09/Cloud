package com.edureka.demo.kafka.consumer;

import com.edureka.demo.kafka.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class CommodityDashboardConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @KafkaListener(topics = "t_commodity", groupId = "cg-dashboard")
    public void consumerMessage(String message){
        var commodity = objectMapper.readValue(message, Commodity.class);
//        Thread.sleep(ThreadLocalRandom.current().nextLong(500,1000));
        log.info("Dashboard logic for {}", commodity);
    }
    
    @SneakyThrows
    @KafkaListener(topics = "t_location",groupId = "cg-far-location", containerFactory = "farLocationContainerFactory")
    public void listenFarThen_100(String message){
        var calLocation = objectMapper.readValue(message, CarLocation.class);
        log.info("ListenFar : {}",calLocation);
    }
}
