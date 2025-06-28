package br.com.dantebasso.central.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "central.kafka")
@Data
public class CentralKafkaConfig {
    
    private String topic;

    private String groupId;
    
}
