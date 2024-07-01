package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.Geolocation;

import static io.restassured.RestAssured.given;

public class GeolocationRoutes {

    private static final Routes config = new Routes();

    public static Response postGeolocationRequest(Geolocation request) {
        return given().
                baseUri(config.getBaseUrl())
                .contentType(ContentType.JSON)
                .body(request)
                .queryParam("key", config.getApiKey())
                .when()
                .post("geolocation/v1/geolocate");
    }

    public static Response postGeolocationRequest(Geolocation request, String apiKey) {
        return given().
                baseUri(config.getBaseUrl())
                .contentType(ContentType.JSON)
                .body(request)
                .queryParam("key", apiKey)
                .when()
                .post("geolocation/v1/geolocate");
    }

    public static Response postGeolocationRequest(Object request) {
        return given().
                baseUri(config.getBaseUrl())
                .contentType(ContentType.JSON)
                .body(request)
                .queryParam("key", config.getApiKey())
                .when()
                .post("geolocation/v1/geolocate");
    }

    public static Response postGeolocationRequest() {
        return given().
                baseUri(config.getBaseUrl())
                .contentType(ContentType.JSON)
                .queryParam("key", config.getApiKey())
                .when()
                .post("geolocation/v1/geolocate");
    }
}
