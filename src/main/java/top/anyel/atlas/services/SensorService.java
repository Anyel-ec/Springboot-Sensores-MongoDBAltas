package top.anyel.atlas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.anyel.atlas.document.SensorData;
import top.anyel.atlas.document.Variables;
import top.anyel.atlas.repositories.SensorDataRepository;

@Service
public class SensorService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    private final String SENSOR_API_URL = "http://192.168.253.158";
    private final RestTemplate restTemplate = new RestTemplate();

    public SensorData getSensorData() {
        return fetchSensorData();
    }

    @Scheduled(fixedRate = 5000) // Muestra datos cada 5 segundos
    public void logSensorData() {
        SensorData sensorData = fetchSensorData();
        if (sensorData != null) {
            sensorDataRepository.save(sensorData);
        }
    }

    private SensorData fetchSensorData() {
        try {
            return restTemplate.getForObject(SENSOR_API_URL, SensorData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}