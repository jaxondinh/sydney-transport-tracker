package com.sydney.transporttracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class GtfsRealtimeService {
    @Value("${tfn.api.key}")
    private String apiKey;
    private RestTemplate restTemplate = new RestTemplate();
    public void fetchSydneyTrainAlerts() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "apikey " + apiKey);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://api.transport.nsw.gov.au/v2/gtfs/alerts/sydneytrains";
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
    }
}
