package br.com.dantebasso.central.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "central.thresholds")
@Data
public class CentralThresholdsConfig {
    
    private Integer temperature;

    private Integer humidity;
    
}
