package com.sydney.transporttracker.controller;

import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.service.AlertService;
import com.sydney.transporttracker.service.GtfsRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;
    // For testing purpose, to be removed
    @Autowired
    private GtfsRealtimeService gtfsRealtimeService;
    @GetMapping("/fetch")
    public String fetchAlerts() {
        gtfsRealtimeService.fetchSydneyTrainAlerts();
        return "Fetch triggered";
    }
    @GetMapping
    public List<Alert> getAlerts() {
        return alertService.getAllAlerts();
    }
    @GetMapping("{id}")
    public Optional<Alert> getAlertByID(@PathVariable Long id) {
        return alertService.getAlertByID(id);
    }
    @PostMapping
    public Alert createAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }
    @DeleteMapping("{id}")
    public boolean deleteAlert(@PathVariable Long id) {
        return alertService.deleteAlert(id);
    }
    @DeleteMapping("/all")
    public boolean deleteAllAlerts() {
        return alertService.deleteAllAlerts();
    }

}
