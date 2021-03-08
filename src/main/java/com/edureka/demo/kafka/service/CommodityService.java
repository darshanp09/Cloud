package com.edureka.demo.kafka.service;

import com.edureka.demo.kafka.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {

    private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();

    private static final String COPPER = "copper";
    private static final String GOLD = "gold";

    private static final double MAX_ADJUSTMENT=1.05;

    private static final double MIN_ADJUSTMENT=0.95;

    static {
        var timestamp = System.currentTimeMillis();
        COMMODITY_BASE.put(GOLD, Commodity.builder().
                name(GOLD).
                price(1_407.25).
                measurement("ounce").
                timestamp(timestamp).
                build());

        COMMODITY_BASE.put(COPPER, Commodity.builder().
                name(COPPER).
                price(5_900.57).
                measurement("tonne").
                timestamp(timestamp).
                build());

    }

    public Commodity createDummyCommodity(String name){
        if(!COMMODITY_BASE.keySet().contains(name)){
            throw new IllegalArgumentException("Invalid Commodity "+name);
        }

        var commodity = COMMODITY_BASE.get(name);
        var basePrice = commodity.getPrice();
        var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT,MAX_ADJUSTMENT);

        commodity.setPrice(newPrice);
        commodity.setTimestamp(System.currentTimeMillis());

        return commodity;
    }

    public List<Commodity> createDummyCommodity(){
        var results = new ArrayList<Commodity>();
        /*for(Map.Entry<String,Commodity> entry: COMMODITY_BASE.entrySet()){
            results.add(entry.getValue());
        }*/
        COMMODITY_BASE.keySet().forEach(c -> results.add(createDummyCommodity(c)));
        return results;
    }
}
