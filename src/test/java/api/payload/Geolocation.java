package api.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geolocation {
    private int homeMobileCountryCode;
    private int homeMobileNetworkCode;
    private String radioType;
    private String carrier;
    private boolean considerIp;
    private CellTower[] cellTowers;

    private WifiAccessPoints[] wifiAccessPoints;
}
