package br.com.dantebasso.central.service;

import br.com.dantebasso.central.config.CentralThresholdsConfig;
import br.com.dantebasso.central.model.DataMeasure;
import br.com.dantebasso.central.model.SensorType;
import br.com.dantebasso.central.service.impl.MonitorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.UUID;

import static br.com.dantebasso.central.utils.ConstantStrings.METADATA_KEY_UTC_TIME;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonitorServiceImplUnitTest {
    
    @Mock
    private NotificationService notificationServiceMock;
    
    @Mock
    private CentralThresholdsConfig centralThresholdsConfigMock;
    
    @InjectMocks
    private MonitorServiceImpl serviceToTest;
    
    @Test
    public void triggerAlarmForTemperatureSensor() {
        //Given
        final DataMeasure dataMeasure = new DataMeasure(
                UUID.randomUUID(),
                "temp1",
                100,
                SensorType.TEMPERATURE,
                Map.of(METADATA_KEY_UTC_TIME, "2025-06-28T18:00:00Z")
        );
        when(centralThresholdsConfigMock.getTemperature()).thenReturn(35);
    
        //When
        serviceToTest.analyze(dataMeasure);
    
        //Then
        verify(notificationServiceMock, times(1)).alarm(
            "Sensor 'temp1' exceeded the temperature limit of '35', reaching '100' (Date/Time: 2025-06-28T18:00:00Z)"
        );
    }
    
    @Test
    public void triggerAlarmForHumiditySensor() {
        //Given
        final DataMeasure dataMeasure = new DataMeasure(
                UUID.randomUUID(),
                "hum10",
                80,
                SensorType.HUMIDITY,
                Map.of(METADATA_KEY_UTC_TIME, "2025-06-28T18:00:00Z")
        );
        when(centralThresholdsConfigMock.getHumidity()).thenReturn(50);
        
        //When
        serviceToTest.analyze(dataMeasure);
        
        //Then
        verify(notificationServiceMock, times(1)).alarm(
            "Sensor 'hum10' exceeded the humidity limit of '50', reaching '80' (Date/Time: 2025-06-28T18:00:00Z)"
        );
    }
    
    @Test
    public void notTriggerAlarmForTemperatureSensor() {
        //Given
        final DataMeasure dataMeasure = new DataMeasure(
                UUID.randomUUID(),
                "temp1",
                10,
                SensorType.TEMPERATURE,
                Map.of(METADATA_KEY_UTC_TIME, "2025-06-28T18:00:00Z")
        );
        when(centralThresholdsConfigMock.getTemperature()).thenReturn(35);
        
        //When
        serviceToTest.analyze(dataMeasure);
        
        //Then
        verify(notificationServiceMock, never()).alarm(anyString());
    }
    
    @Test
    public void notTriggerAlarmForHumiditySensor() {
        //Given
        final DataMeasure dataMeasure = new DataMeasure(
                UUID.randomUUID(),
                "humidity-sensor-1",
                89,
                SensorType.HUMIDITY,
                Map.of(METADATA_KEY_UTC_TIME, "2025-06-28T18:00:00Z")
        );
        when(centralThresholdsConfigMock.getHumidity()).thenReturn(90);
        
        //When
        serviceToTest.analyze(dataMeasure);
        
        //Then
        verify(notificationServiceMock, never()).alarm(anyString());
    }
    
}
