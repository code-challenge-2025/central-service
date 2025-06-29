package br.com.dantebasso.central.service.impl;

import br.com.dantebasso.central.config.CentralThresholdsConfig;
import br.com.dantebasso.central.model.DataMeasure;
import br.com.dantebasso.central.model.SensorType;
import br.com.dantebasso.central.service.MonitorService;
import br.com.dantebasso.central.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.dantebasso.central.utils.ConstantStrings.METADATA_KEY_UTC_TIME;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitorServiceImpl implements MonitorService {
    
    private final CentralThresholdsConfig centralThresholdsConfig;
    private final NotificationService notificationService;
    
    @Override
    public void analyze(final DataMeasure dataMeasure) {
        if (dataMeasure != null) {
            log.info("Starting analysis of sensor with Sensor: '{}/{}'", dataMeasure.id(), dataMeasure.sensorId());
            if (dataMeasure.sensorType().equals(SensorType.TEMPERATURE)) {
                evaluateTemperatureSensor(dataMeasure);
            } else {
                evaluateHumiditySensor(dataMeasure);
            }
        }
    }
    
    protected void evaluateTemperatureSensor(final DataMeasure dataMeasure) {
        if (dataMeasure.sensorValue() > centralThresholdsConfig.getTemperature()) {
            final String message =
                    String.format(
                            "Sensor '%s' exceeded the temperature limit of '%s', reaching '%s' (Date/Time: %s)",
                            dataMeasure.sensorId(),
                            centralThresholdsConfig.getTemperature(),
                            dataMeasure.sensorValue(),
                            dataMeasure.metadata().get(METADATA_KEY_UTC_TIME)
                    );
            notificationService.alarm(message);
        }
    }
    
    protected void evaluateHumiditySensor(final DataMeasure dataMeasure) {
        if (dataMeasure.sensorValue() > centralThresholdsConfig.getHumidity()) {
            final String message =
                    String.format(
                            "Sensor '%s' exceeded the humidity limit of '%s', reaching '%s' (Date/Time: %s)",
                            dataMeasure.sensorId(),
                            centralThresholdsConfig.getHumidity(),
                            dataMeasure.sensorValue(),
                            dataMeasure.metadata().get(METADATA_KEY_UTC_TIME)
                    );
            notificationService.alarm(message);
        }
    }
    
}
