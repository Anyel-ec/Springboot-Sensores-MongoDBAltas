package top.anyel.atlas.services;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 25/02/2025
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class SensorWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {}

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    @Scheduled(fixedRate = 2000) // Envía datos cada 2 segundos
    public void sendSensorData() {
        if (!sessions.isEmpty()) {
            String jsonData = generateSensorJson();
            synchronized (sessions) {
                for (WebSocketSession session : sessions) {
                    try {
                        session.sendMessage(new TextMessage(jsonData));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String generateSensorJson() {
        double temperatura = ThreadLocalRandom.current().nextDouble(20.0, 30.0);
        double humedad = ThreadLocalRandom.current().nextDouble(50.0, 80.0);

        return """
        {
            \"variables\": {
                \"Temperatura\": " + temperatura + ",
                \"Humedad\": " + humedad + ",
                \"Contaminacion\": 1
            },
            \"id\": \"1\",
            \"name\": \"sensor_wemos\",
            \"hardware\": \"esp8266\",
            \"connected\": true
        }
        """;
    }
}
