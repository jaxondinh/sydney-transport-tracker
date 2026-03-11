package com.sydney.transporttracker.service;

import com.sydney.transporttracker.exception.AlertNotFoundException;
import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.repository.AlertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {
    @Mock
    private AlertRepository alertRepository;
    @InjectMocks
    private AlertService alertService;
    private Alert createTestAlert(String gtfsAlertId) {
        Alert alert = new Alert();
        alert.setTitle("Test Title");
        alert.setReason("Test Reason");
        alert.setStatus("Test Status");
        alert.setLineName("Test LineName");
        alert.setAffectedStops("Test Affected Stops");
        alert.setStartTime(null);
        alert.setEndTime(null);
        alert.setGtfsAlertId(gtfsAlertId);
        return alert;
    }
    // Happy path for findById
    @Test
    void getAlertById_alertExists_returnsAlert() {
        // Arrange
        Alert alert = createTestAlert("Test");
        when(alertRepository.findById(1L)).thenReturn(Optional.of(alert));
        // Act
        Alert result = alertService.getAlertById(1L);
        // Assert
        assertEquals(alert, result);
    }
    // Unhappy path for findById
    @Test
    void getAlertById_alertNotFound_throwsAlertNotFoundException() {
        // Arrange
        when(alertRepository.findById(1L)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(AlertNotFoundException.class, ()-> {
         alertService.getAlertById(1L);
        });
    }
    // Happy path for deleteById
    @Test
    void deleteAlert_alertExists_deleteSuccess() {
        // Arrange
        Alert alert = createTestAlert("Test");
        when(alertRepository.findById(1L)).thenReturn(Optional.of(alert));
        // Act
        alertService.deleteAlert(1L);
        // Assert
        verify(alertRepository, times(1)).deleteById(1L);
    }
    // Unhappy path for deleteById
    @Test
    void deleteAlert_alertNotFound_throwsAlertNotFoundException() {
        // Arrange
        when(alertRepository.findById(1L)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(AlertNotFoundException.class, ()-> {
            alertService.deleteAlert(1L);
        });
    }
    // Happy path for existsByGtfsAlertId
    @Test
    void existsByGtfsAlertId_alertFound_returnsTrue() {
        // Arrange
        when(alertRepository.existsByGtfsAlertId("Test GTFSAlertId")).thenReturn(true);
        // Act
        boolean result = alertService.existsByGtfsAlertId("Test GTFSAlertId");
        // Assert
        assertTrue(result);
    }
    // Unhappy path for existsByGtfsAlertId
    @Test
    void existsByGtfsAlertId_alertNotFound_returnsFalse() {
        // Arrange
        when(alertRepository.existsByGtfsAlertId("Invalid-GTFSAlertId")).thenReturn(false);
        // Act
        boolean result = alertService.existsByGtfsAlertId("Invalid-GTFSAlertId");
        // Assert
        assertFalse(result);
    }
    // Happy path for createAlert
    @Test
    void createAlert_validAlert_returnsSavedAlert() {
        // Arrange
        Alert alert = createTestAlert("Test");
        when(alertRepository.save(alert)).thenReturn(alert);
        // Act
        Alert result = alertService.createAlert(alert);
        // Assert
        assertEquals(alert, result);
    }
    // Happy path for getAllAlerts
    @Test
    void getAllAlerts_returnsListOfAlerts() {
        // Arrange
        Alert alert1 = createTestAlert("Test");
        Alert alert2 = createTestAlert("Test2");
        when(alertRepository.findAll()).thenReturn(Arrays.asList(alert1, alert2));
        // Act
        List<Alert> result = alertService.getAllAlerts();
        // Assert
        assertEquals(Arrays.asList(alert1, alert2), result);
    }
    // Happy path for deleteAllAlerts
    @Test
    void deleteAllAlerts_deleteSuccessfully() {
        // Act
        alertService.deleteAllAlerts();
        // Assert
        verify(alertRepository, times(1)).deleteAll();
    }
}
