package api.test;

import api.endpoints.GeolocationRoutes;
import api.payload.CellTower;
import api.payload.Geolocation;
import api.payload.WifiAccessPoints;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class GeolocationTest {

    @Test(priority = -1)
    void postGeolocationRequest() {
        // Request data Setup
        Geolocation request = new Geolocation();
        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        WifiAccessPoints wifiAccessPoints1 = new WifiAccessPoints();

        wifiAccessPoints.setMacAddress("3c:37:86:5d:75:d4");
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setSignalToNoiseRatio(0);

        wifiAccessPoints1.setMacAddress("30:86:2d:c4:29:d0");
        wifiAccessPoints1.setSignalStrength(-35);
        wifiAccessPoints1.setSignalToNoiseRatio(0);

        request.setConsiderIp(false);
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints, wifiAccessPoints1});

        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request);

        // Assertions
        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.getBody().jsonPath().get("accuracy"));
        assertNotNull(response.getBody().jsonPath().get("location"));
        assertNotNull(response.getBody().jsonPath().get("location.lat"));
        assertNotNull(response.getBody().jsonPath().get("location.lng"));
    }


    @Test
    void geolocationRequest_ValidInputs_ShouldReturnSuccess() {
        // Request data Setup
        Geolocation request = new Geolocation();

        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        wifiAccessPoints.setMacAddress("3c:37:86:5d:75:d4");
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setSignalToNoiseRatio(0);

        WifiAccessPoints wifiAccessPoints1 = new WifiAccessPoints();
        wifiAccessPoints1.setMacAddress("30:86:2d:c4:29:d0");
        wifiAccessPoints1.setSignalStrength(-35);
        wifiAccessPoints1.setSignalToNoiseRatio(0);

        CellTower cellTower = new CellTower();
        cellTower.setCellId(170402199L);
        cellTower.setLocationAreaCode(35632);
        cellTower.setMobileCountryCode(310);
        cellTower.setMobileNetworkCode(410);
        cellTower.setAge(0);
        cellTower.setSignalStrength(-60.00);
        cellTower.setTimingAdvance(15.00);


        request.setHomeMobileCountryCode(310);
        request.setHomeMobileNetworkCode(410);
        request.setRadioType("gsm");
        request.setCarrier("Vodafone");
        request.setConsiderIp(true);
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints, wifiAccessPoints1});
        request.setCellTowers(new CellTower[]{cellTower});


        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request);

        // Assertions
        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.getBody().jsonPath().get("accuracy"));
        assertNotNull(response.getBody().jsonPath().get("location"));
        assertNotNull(response.getBody().jsonPath().get("location.lat"));
        assertNotNull(response.getBody().jsonPath().get("location.lng"));
    }

    @Test
    void geolocationRequest_Valid5GNetworkAndNewCellId_ShouldReturnSuccess() {
        // Request data Setup
        Geolocation request = new Geolocation();

        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        wifiAccessPoints.setMacAddress("3c:37:86:5d:75:d4");
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setSignalToNoiseRatio(0);

        WifiAccessPoints wifiAccessPoints1 = new WifiAccessPoints();
        wifiAccessPoints1.setMacAddress("30:86:2d:c4:29:d0");
        wifiAccessPoints1.setSignalStrength(-35);
        wifiAccessPoints1.setSignalToNoiseRatio(0);

        CellTower cellTower = new CellTower();
        cellTower.setNewRadioCellId(68719476735L);
        cellTower.setMobileCountryCode(310);
        cellTower.setMobileNetworkCode(410);
        cellTower.setAge(0);
        cellTower.setSignalStrength(-60.00);

        request.setHomeMobileCountryCode(310);
        request.setHomeMobileNetworkCode(410);
        request.setRadioType("NR");
        request.setCarrier("Vodafone");
        request.setConsiderIp(true);
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints, wifiAccessPoints1});
        request.setCellTowers(new CellTower[]{cellTower});


        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request);

        response.then().log().body();

        // Assertions
        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.getBody().jsonPath().get("accuracy"));
        assertNotNull(response.getBody().jsonPath().get("location"));
        assertNotNull(response.getBody().jsonPath().get("location.lat"));
        assertNotNull(response.getBody().jsonPath().get("location.lng"));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "getRadioTypeTestData")
    void geolocationRequest_InvalidRadioTypeAndNewCellId_ShouldReturnBadResponse(String radioType) {
        // Request data Setup
        Geolocation request = new Geolocation();

        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setMacAddress("3c:37:86:5d:75:d4");
        wifiAccessPoints.setSignalToNoiseRatio(0);

        WifiAccessPoints wifiAccessPoints1 = new WifiAccessPoints();
        wifiAccessPoints1.setMacAddress("3d:37:86:5f:77:d4");
        wifiAccessPoints1.setSignalStrength(-35);
        wifiAccessPoints1.setSignalToNoiseRatio(0);

        CellTower cellTower = new CellTower();
        cellTower.setNewRadioCellId(68719476735L);
        cellTower.setMobileCountryCode(310);
        cellTower.setMobileNetworkCode(410);
        cellTower.setAge(0);
        cellTower.setSignalStrength(-60.00);

        request.setHomeMobileCountryCode(1000);
        request.setHomeMobileNetworkCode(410);
        request.setRadioType(radioType);
        request.setCarrier("Vodafone");
        request.setConsiderIp(true);
        request.setCellTowers(new CellTower[]{cellTower});
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints, wifiAccessPoints1});


        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request);
        response.then().log().body();

        JSONObject jsonObj = new JSONObject(response.asString());
        // Assertions
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.getBody().jsonPath().getString("error.message"), "INVALID_ARGUMENT: Type " + radioType.toUpperCase() + " cell tower objects must have a \"cellId\" field.");
        assertTrue(jsonObj.getJSONObject("error").getJSONArray("errors").length() >= 1);
    }

    @Test
    void geolocationRequest_AccessPointMissingMacAddress_ShouldReturnBadResponse() {
        // Request data Setup
        Geolocation request = new Geolocation();

        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setSignalToNoiseRatio(0);

        WifiAccessPoints wifiAccessPoints1 = new WifiAccessPoints();
        wifiAccessPoints1.setSignalStrength(-35);
        wifiAccessPoints1.setSignalToNoiseRatio(0);

        request.setHomeMobileCountryCode(1000);
        request.setHomeMobileNetworkCode(410);
        request.setRadioType("gsm");
        request.setCarrier("Vodafone");
        request.setConsiderIp(true);
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints, wifiAccessPoints1});


        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request);
        response.then().log().body();

        JSONObject jsonObj = new JSONObject(response.asString());
        // Assertions
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.getBody().jsonPath().getString("error.message"), "INVALID_ARGUMENT: WiFi access point objects must have a \"macAddress\" field.");
        assertTrue(jsonObj.getJSONObject("error").getJSONArray("errors").length() >= 1);
    }


    @Test
    void geolocationRequest_WithoutRequestBody_ShouldReturnSuccess() {
        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest();

        // Assertions
        assertEquals(response.getStatusCode(), 200);
        assertNotNull(response.getBody().jsonPath().get("accuracy"));
        assertNotNull(response.getBody().jsonPath().get("location"));
        assertNotNull(response.getBody().jsonPath().get("location.lat"));
        assertNotNull(response.getBody().jsonPath().get("location.lng"));
    }

    @Test
    void geolocationRequest_WithInvalidRequestBody_ShouldReturnParseFailure() {
        String jsonString = "{\n" +
                "  \"considerIp\": \"123\",\n" +
                "  \"wifiAccessPoints\": [\n" +
                "    {\n" +
                "      \"macAddress\": \"3c:37:86:5d:75:d4\",\n" +
                "      \"signalStrength\": -35,\n" +
                "      \"signalToNoiseRatio\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"macAddress\": \"30:86:2d:c4:29:d0\",\n" +
                "      \"signalStrength\": -35,\n" +
                "      \"signalToNoiseRatio\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(jsonString);

        // Assertions
        assertEquals(response.getStatusCode(), 400);
        assertTrue(response.getBody().jsonPath().getString("error.message").contains("Invalid value at"));
        assertEquals(response.getBody().jsonPath().getString("error.status"), "INVALID_ARGUMENT");
    }

    @Test
    void geolocationRequest_WithInvalidApiKey_ShouldReturnFailure() {
        // Request data Setup
        Geolocation request = new Geolocation();
        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        WifiAccessPoints wifiAccessPoints1 = new WifiAccessPoints();

        wifiAccessPoints.setMacAddress("3c:37:86:5d:75:d4");
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setSignalToNoiseRatio(0);

        wifiAccessPoints1.setMacAddress("30:86:2d:c4:29:d0");
        wifiAccessPoints1.setSignalStrength(-35);
        wifiAccessPoints1.setSignalToNoiseRatio(0);

        request.setConsiderIp(false);
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints, wifiAccessPoints1});

        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request, "invalidApiKey");

        JSONObject jsonObject = new JSONObject(response.asString());
        int errorsArraySize = jsonObject.getJSONObject("error").getJSONArray("errors").length();

        for (int i = 0; i < errorsArraySize; i++) {
            String expectedReason = "badRequest";
            String actualReason = jsonObject.getJSONObject("error").getJSONArray("errors").getJSONObject(i).getString("reason");
            assertEquals(actualReason, expectedReason);
        }

        // Assertions
        assertEquals(response.getStatusCode(), 400);
        assertEquals(response.getBody().jsonPath().getString("error.message"), "API key not valid. Please pass a valid API key.");
        assertEquals(response.getBody().jsonPath().getString("error.status"), "INVALID_ARGUMENT");
    }

    @Test
    void geolocationRequest_WithInsufficientAccessPoints_ShouldReturnNotFound() {
        String[] macs = {"12:34:56:78:9a:bc", "1c:34:56:78:9a:bc", "00:00:5e:00:00:01" };
        ArrayList<String> _macs = new ArrayList<>(Arrays.asList(macs));
        _macs.removeIf(m -> !(0 == (2 & Integer.parseInt(m.substring(1, 2), 16))
                && !m.substring(0, 8).toUpperCase().equals("00:00:5E")));

        // Request data Setup
        Geolocation request = new Geolocation();

        WifiAccessPoints wifiAccessPoints = new WifiAccessPoints();
        wifiAccessPoints.setMacAddress(_macs.get(0));
        wifiAccessPoints.setSignalStrength(-35);
        wifiAccessPoints.setSignalToNoiseRatio(0);

        request.setConsiderIp(false);
        request.setWifiAccessPoints(new WifiAccessPoints[]{wifiAccessPoints});

        // Hitting the geolocation api endpoint
        Response response = GeolocationRoutes.postGeolocationRequest(request);

        // Assertions
        assertEquals(response.getStatusCode(), 404);
        assertEquals(response.getBody().jsonPath().getString("error.message"), "Not Found");
    }
}
