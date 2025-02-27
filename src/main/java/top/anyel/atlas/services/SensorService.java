package top.anyel.atlas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.anyel.atlas.document.SensorData;
import top.anyel.atlas.document.Variables;
import top.anyel.atlas.repositories.SensorDataRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SensorService {

    @Autowired
    private SensorDataRepository sensorDataRepository;
    public SensorData getSensorData() {
        return generateSensorData();
    }

    @Scheduled(fixedRate = 5000) // Muestra datos cada 5 segundos
    public void logSensorData() {
        SensorData sensorData = generateSensorData();
        System.out.println(sensorData);
    }

    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }
    public Page<SensorData> getSensorDataPaginated(int page) {
        return sensorDataRepository.findAll(PageRequest.of(page, 5));
    }

    private SensorData generateSensorData() {
        double temperatura = Math.round(ThreadLocalRandom.current().nextDouble(27.7, 28.1) * 10.0) / 10.0;
        double humedad = Math.round(ThreadLocalRandom.current().nextDouble(70.8, 72.3) * 10.0) / 10.0;
        Variables variables = new Variables(temperatura, humedad, 1);
        sensorDataRepository.save(new SensorData(variables, null, "sensor_wemos", "esp8266", true));
        return new SensorData(variables, null, "sensor_wemos", "esp8266", true);
    }
}