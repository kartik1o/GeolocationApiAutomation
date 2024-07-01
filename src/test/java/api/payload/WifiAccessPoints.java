package api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiAccessPoints {
    private String macAddress;
    private int signalStrength;
    private int age;
    private int channel;
    private int signalToNoiseRatio;
}
