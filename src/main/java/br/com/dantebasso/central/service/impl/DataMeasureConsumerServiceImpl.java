package br.com.dantebasso.central.service.impl;

import br.com.dantebasso.central.model.DataMeasure;
import br.com.dantebasso.central.service.DataMeasureConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataMeasureConsumerServiceImpl implements DataMeasureConsumerService {
    
    @Override
    @KafkaListener(topics = "#{@centralKafkaConfig.topic}", groupId = "#{@centralKafkaConfig.groupId}")
    public void consume(DataMeasure dataMeasure) {
        log.info("Received message from Kafka: {}", dataMeasure);
    }
    
}
