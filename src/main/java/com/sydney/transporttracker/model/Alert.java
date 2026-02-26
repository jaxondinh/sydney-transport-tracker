package com.sydney.transporttracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lineName;
    private String status;
    private String reason;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String affectedStops;
}
