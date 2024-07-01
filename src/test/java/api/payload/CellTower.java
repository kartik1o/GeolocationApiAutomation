package api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CellTower {
    private Long cellId;
    private Long newRadioCellId;
    private int locationAreaCode;
    private int mobileCountryCode;
    private int mobileNetworkCode;
    // Optional properties
    private int age;
    private Double signalStrength;
    private Double timingAdvance;
}
