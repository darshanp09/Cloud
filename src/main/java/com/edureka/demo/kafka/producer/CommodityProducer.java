package com.edureka.demo.kafka.producer;

import com.edureka.demo.kafka.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommodityProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public void sendMessage(Commodity commodity){
        var json = objectMapper.writeValueAsString(commodity);
        kafkaTemplate.send("t_commodity", commodity.getName() ,json);
    }
}
