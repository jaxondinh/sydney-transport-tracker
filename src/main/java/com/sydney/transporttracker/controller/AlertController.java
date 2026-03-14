package com.sydney.transporttracker.controller;

import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.service.AlertService;
import com.sydney.transporttracker.service.GtfsRealtimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    @GetMapping
    public List<Alert> getAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("{id}")
    public Alert getAlertByID(@PathVariable Long id) {
        return alertService.getAlertById(id);
    }

    @PostMapping
    public Alert createAlert(@Valid @RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
    }

    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllAlerts() {
        alertService.deleteAllAlerts();
    }

}
