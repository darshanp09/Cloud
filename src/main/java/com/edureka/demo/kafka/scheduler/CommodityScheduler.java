package com.edureka.demo.kafka.scheduler;

import com.edureka.demo.kafka.entity.Commodity;
import com.edureka.demo.kafka.producer.CommodityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommodityScheduler {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer producer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities(){
        var commodity = restTemplate.exchange("http://localhost:8080/api/commodity/v1/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Commodity>>() {
        }).getBody();

        commodity.forEach(c -> producer.sendMessage(c));
    }
}
