package top.anyel.atlas.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 25/02/2025
 */
@Data
@NoArgsConstructor
@Document
@RequiredArgsConstructor
@Builder
public class SensorData {
    @JsonProperty("variables")
    private Variables variables;
    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("hardware")
    private String hardware;

    @JsonProperty("connected")
    private boolean connected;


    public SensorData(Variables variables, String id, String name, String hardware, boolean connected) {
        this.variables = variables;
        this.id = id;
        this.name = name;
        this.hardware = hardware;
        this.connected = connected;

    }
}
