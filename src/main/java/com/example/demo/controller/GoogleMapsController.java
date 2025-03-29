package com.example.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("api/maps")
@CrossOrigin
@RestController
public class GoogleMapsController {

    private final String API_KEY = "AIzaSyANfIskDROp9Q9UCONXmTuWiT9RX9WbRdA";

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @GetMapping("/autocomplete")
    public ResponseEntity<String> getAutocomplete(@RequestParam String input) {
        try {
            System.setProperty("http.proxyHost", "your.proxy.server");
            System.setProperty("http.proxyPort", "8080");  // שנה את מספר הפורט אם צריך
            System.setProperty("https.proxyHost", "your.proxy.server");
            System.setProperty("https.proxyPort", "8080");  // שנה את מספר הפורט אם צריך
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + input + "&key=" + googleMapsApiKey;
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error connecting to Google Maps API");
        }
    }

    @GetMapping("/geocode")
    public ResponseEntity<?> getGeocode(@RequestParam String address) {
        System.setProperty("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-23\\lib\\security\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");  // ברוב המקרים הסיסמה ברירת המחדל היא "changeit"
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                + URLEncoder.encode(address, StandardCharsets.UTF_8) + "&key=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return ResponseEntity.ok(response.getBody());
    }

//    @GetMapping("/autocomplete")
//    public ResponseEntity<?> getAutocompleteSuggestions(@RequestParam String input) {
//        String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
//                + URLEncoder.encode(input, StandardCharsets.UTF_8) + "&key=" + API_KEY;
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        return ResponseEntity.ok(response.getBody());
//
//    }

    @GetMapping("/coordinates")
    public ResponseEntity<?> getCoordinates(@RequestParam String address) {
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                    + URLEncoder.encode(address, StandardCharsets.UTF_8) + "&key=" + API_KEY;

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode location = jsonNode.path("results").get(0).path("geometry").path("location");

            Map<String, Double> coordinates = new HashMap<>();
            coordinates.put("lat", location.path("lat").asDouble());
            coordinates.put("lng", location.path("lng").asDouble());

            return ResponseEntity.ok(coordinates);

        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500)
                    .body("Error processing the response from Google Maps API");
        }
    }



}