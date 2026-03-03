package com.sydney.transporttracker.service;

import com.sydney.transporttracker.exception.AlertNotFoundException;
import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;
    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }
    public void deleteAlert(Long alertID) {
        // will throw an exception if not found, ending execution
        getAlertByID(alertID);
        alertRepository.deleteById(alertID);
    }
    public boolean deleteAllAlerts() {
        alertRepository.deleteAll();
        return true;
    }
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
    public Alert getAlertByID(Long alertID) {
        return alertRepository.findById(alertID).orElseThrow(() -> new AlertNotFoundException(alertID));
    }
    public boolean existsByGtfsAlertId(String gtfsAlertId) {
        return alertRepository.existsByGtfsAlertId(gtfsAlertId);
    }
}
