package top.anyel.atlas.document;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 25/02/2025
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class Variables {
    @JsonProperty("Temperatura")
    private double temperatura;

    @JsonProperty("Humedad")
    private double humedad;

    @JsonProperty("Contaminacion")
    private int contaminacion;

    public Variables (double temperatura, double humedad, int contaminacion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.contaminacion = contaminacion;
    }
}
