package com.sydney.transporttracker.service;

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
    public boolean deleteAlert(Long alertID) {
        alertRepository.deleteById(alertID);
        return true;
    }
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
    public Optional<Alert> getAlertByID(Long alertID) {
        return alertRepository.findById(alertID);
    }
}
