package com.sydney.transporttracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lineName;
    private String status;
    private String reason;
    private LocalTime startTime;
    private LocalTime endTime;
    private String affectedStops;
}
