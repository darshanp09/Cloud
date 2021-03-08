package com.edureka.demo.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {

    private String name;
    private double price;
    private String measurement;
    private long timestamp;

    public void setPrice(double price) {
        this.price = Math.round(price * 100d)/100d;
    }
}
