package com.sydney.transporttracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "gtfsAlertId is mandatory")
    private String gtfsAlertId;
    @Column(columnDefinition = "TEXT")
    private String title;
    @NotBlank(message = "lineName is mandatory")
    private String lineName;
    @NotBlank(message = "status is mandatory")
    private String status;
    @Column(columnDefinition = "TEXT")
    private String reason;
    @NotNull(message = "startTime is mandatory")
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "affectedStops is mandatory")
    private String affectedStops;
}
