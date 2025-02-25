package top.anyel.atlas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.anyel.atlas.document.SensorData;
import top.anyel.atlas.services.SensorService;
import org.springframework.http.MediaType;
/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 25/02/2025
 */

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SensorData> getSensorData() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sensorService.getSensorData());
    }
}
