package com.sydney.transporttracker.service;

import com.google.transit.realtime.GtfsRealtime;
import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.model.User;
import com.sydney.transporttracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

@Service
public class GtfsRealtimeService {
    @Value("${tfn.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private AlertService alertService;
    @Autowired
    private EmailService emailService;
    @Autowired
    UserRepository userRepository;
    @Scheduled(fixedRate = 300000)
    public void fetchSydneyTrainAlerts() {
        System.out.println("Scheduled fetch triggered at: " + LocalDateTime.now());
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
            for (GtfsRealtime.FeedEntity e : feed.getEntityList()) {
                if (e.hasAlert()) {
                    GtfsRealtime.Alert gtfsAlert = e.getAlert();
                    Alert alert = new Alert();
                    alert.setTitle(gtfsAlert.getHeaderText().getTranslation(0).getText());
                    alert.setReason(gtfsAlert.getDescriptionText().getTranslation(0).getText());
                    alert.setStatus(gtfsAlert.getEffect().toString());
                    alert.setLineName(gtfsAlert.getInformedEntity(0).getRouteId());
                    alert.setAffectedStops(gtfsAlert.getInformedEntity(0).getRouteId());
                    alert.setStartTime(LocalDateTime.ofEpochSecond(gtfsAlert.getActivePeriod(0).getStart(), 0, ZoneOffset.UTC));
                    long endTimestamp = gtfsAlert.getActivePeriod(0).getEnd();
                    alert.setEndTime(endTimestamp == 0 ? null : LocalDateTime.ofEpochSecond(endTimestamp, 0, ZoneOffset.UTC));
                    alert.setGtfsAlertId(e.getId());
                    if (!alertService.existsByGtfsAlertId(alert.getGtfsAlertId())) {
                        alertService.createAlert(alert);
                        List<User> users = userRepository.findByNotificationsEnabled(true);
                        String body = "New Sydney Train Alert\n\n" +
                                "Line: " + alert.getLineName() + "\n" +
                                "Status: " + alert.getStatus() + "\n" +
                                "Start time:" + alert.getStartTime() + "\n\n" +
                                "Details: " + alert.getReason();
                        for (User user : users) {
                            emailService.sendEmail(user.getEmail(), alert.getTitle(), body);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching GTFS data: " + e.getMessage());
        }
    }
}
