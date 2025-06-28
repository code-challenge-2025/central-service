package br.com.dantebasso.central.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "warehouse.kafka")
@Data
public class WarehouseKafkaConfig {
    
    private String topic;

    private String groupId;
    
}
