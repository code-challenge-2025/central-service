package br.com.dantebasso.central.config;

import br.com.dantebasso.central.model.DataMeasure;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    
    private final CentralKafkaConfig centralKafkaConfig;
    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DataMeasure> kafkaListenerContainerFactory(
            ConsumerFactory<String, DataMeasure> consumerFactory) {
        
        final ConcurrentKafkaListenerContainerFactory<String, DataMeasure> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
    
}
