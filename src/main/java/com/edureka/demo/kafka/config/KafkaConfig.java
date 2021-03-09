package com.edureka.demo.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory(){
        var properties = kafkaProperties.buildConsumerProperties();

        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG,"120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }
    
    @SneakyThrows
    @Bean(name = "farLocationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationFactory
            ( ConcurrentKafkaListenerContainerFactoryConfigurer configure){

        var factory = new ConcurrentKafkaListenerContainerFactory<Object,Object>();
        configure.configure(factory, consumerFactory());

        // TODO: define filter now
        factory.setRecordFilterStrategy(new RecordFilterStrategy<Object, Object>() {

            @SneakyThrows
            @Override
            public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
                var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
                return carLocation.getDistance() <= 100;
            }
        });

        return factory;
    }
}
