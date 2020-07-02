package com.example.demo.service;


import com.example.demo.service.model.Page;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class RestClient {

    private final String server = "http://interview.agileengine.com/";
    private RestTemplate rest;
    private HttpHeaders headers;
    private Gson gson = new Gson();
    private String apiKey = "23567b218376f79d9415";  //todo move to the properties
    public String token;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        login();
    }

    private void login() {
        HttpEntity<String> requestEntity = new HttpEntity<>("{ \"apiKey\":\"" + apiKey + "\"}", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + "auth", HttpMethod.POST, requestEntity, String.class);
        responseEntity.getBody();
        JsonObject jsonObject = gson.fromJson(responseEntity.getBody(), JsonObject.class);
        if (!jsonObject.get("auth").getAsBoolean()) {
            throw new RuntimeException(); // todo change to custom one
        }
        String token = jsonObject.get("token").toString();
        this.token = token.substring(1, token.length() - 1); // remove "
        this.headers.set("Authorization", "Bearer " + this.token);
    }

    public <T> T get(String uri, Class<T> imagesResponseClass) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, imagesResponseClass);
        if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            login();
            responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, imagesResponseClass);
        }
        return responseEntity.getBody();
    }
}
