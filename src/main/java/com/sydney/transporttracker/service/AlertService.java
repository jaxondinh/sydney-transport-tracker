package com.sydney.transporttracker.service;

import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;
    public Alert createAlert(Alert alert) {
        alertRepository.save(alert);
        return alert;
    }
    public boolean deleteAlert(Alert alert) {
        alertRepository.deleteById(alert.id);
        return true;
    }
    public List<Alert> getAllAlerts() {
        List<Alert> alertList = alertRepository.findAll();
        return alertList;
    }
    public Alert getAlertByID(Long alertID) {
        Alert alert = alertRepository.getAlertById(alertID);
        return alert;
    }
}
