package com.sydney.transporttracker.service;

import com.sydney.transporttracker.exception.AlertNotFoundException;
import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.repository.AlertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {
    @Mock
    private AlertRepository alertRepository;
    @InjectMocks
    private AlertService alertService;
    // Happy path for findById
    @Test
    void findAlertById() {
        // Arrange
        Alert alert = new Alert();
        alert.setTitle("Test Title");
        alert.setReason("Test Reason");
        alert.setStatus("Test Status");
        alert.setLineName("Test LineName");
        alert.setAffectedStops("Test Affected Stops");
        alert.setStartTime(null);
        alert.setEndTime(null);
        alert.setGtfsAlertId("Test GTFSAlertId");
        when(alertRepository.findById(1L)).thenReturn(Optional.of(alert));
        // Act
        Alert result = alertService.getAlertById(1L);
        // Assert
        assertEquals(alert, result);
    }
    // Unhappy path for findById
    @Test
    void findAlertByIdFail() {
        // Arrange
        when(alertRepository.findById(1L)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(AlertNotFoundException.class, ()-> {
         alertService.getAlertById(1L);
        });
    }
}
