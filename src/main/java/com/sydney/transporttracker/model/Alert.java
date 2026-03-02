package com.sydney.transporttracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String title;
    private String lineName;
    private String status;
    @Column(columnDefinition = "TEXT")
    private String reason;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Column(columnDefinition = "TEXT")
    private String affectedStops;
}
