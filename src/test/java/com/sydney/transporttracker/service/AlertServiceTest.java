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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {
    @Mock
    private AlertRepository alertRepository;
    @InjectMocks
    private AlertService alertService;
    // Happy path for findById
    @Test
    void getAlertById_alertExists_returnsAlert() {
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
    @Test
    void existsByGtfsAlertId_alertFound_returnsFalse() {
        // Arrange
        when(alertRepository.existsByGtfsAlertId("Invalid-GTFSAlertId")).thenReturn(false);
        // Act
        boolean result = alertService.existsByGtfsAlertId("Invalid-GTFSAlertId");
        // Assert
        assertFalse(result);
    }
}
