package com.sydney.transporttracker.service;

import com.google.transit.realtime.GtfsRealtime;
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
        try {
            // Create header
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "apikey " + apiKey);
            // Tell header what is acceptable, always take list so use singletonList here
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
            // Wrapper needed to be used with restTemplate
            HttpEntity<String> entity = new HttpEntity<>(headers);
            // Actual API call
            String url = "https://api.transport.nsw.gov.au/v2/gtfs/alerts/sydneytrains";
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            // Parsing
            byte[] data = response.getBody();
            GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(data);
        } catch (Exception e) {
            System.out.println("Error fetching GTFS data" + e.getMessage());
        }
    }
}
