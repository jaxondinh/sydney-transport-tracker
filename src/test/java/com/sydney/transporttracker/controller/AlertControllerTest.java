package com.sydney.transporttracker.controller;

import com.sydney.transporttracker.exception.AlertNotFoundException;
import com.sydney.transporttracker.model.Alert;
import com.sydney.transporttracker.service.AlertService;
import com.sydney.transporttracker.service.GtfsRealtimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import java.util.Arrays;

@WebMvcTest(AlertController.class)
public class AlertControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlertService alertService;

    @MockitoBean
    private GtfsRealtimeService gtfsRealtimeService;

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
    // Happy path for GET request on /alerts
    @Test
    void getAlerts_successful() throws Exception {
        // Arrange
        Alert alert1 = createTestAlert("test1");
        Alert alert2 = createTestAlert("test2");
        when(alertService.getAllAlerts()).thenReturn(Arrays.asList(alert1, alert2));
        // Act & Assert
        mockMvc.perform(get("/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].gtfsAlertId").value("test1"))
                .andExpect(jsonPath("$[1].gtfsAlertId").value("test2"));
    }
    // Happy path for GET request on /alerts/{id}
    @Test
    void getAlertsById_successful() throws Exception {
        // Arrange
        Alert alert1 = createTestAlert("test1");
        when(alertService.getAlertById(1L)).thenReturn(alert1);
        // Act & Assert
        mockMvc.perform(get("/alerts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.gtfsAlertId").value("test1"));
    }
    // Unhappy path for GET request on /alerts/{id}
    @Test
    void getAlertsById_unsuccessful() throws Exception {
        // Arrange
        when(alertService.getAlertById(1L)).thenThrow(new AlertNotFoundException(1L));
        // Act & Assert

    }
}
