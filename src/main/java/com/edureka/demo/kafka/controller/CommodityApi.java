package com.edureka.demo.kafka.controller;

import com.edureka.demo.kafka.entity.Commodity;
import com.edureka.demo.kafka.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

    @Autowired
    private CommodityService service;

    @GetMapping("/all")
    public List<Commodity> generatedCommodity(){
        return service.createDummyCommodity();
    }
}
