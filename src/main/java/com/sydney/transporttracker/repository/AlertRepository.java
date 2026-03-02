package com.sydney.transporttracker.repository;

import com.sydney.transporttracker.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    boolean existsByGtfsAlertId(String gtfsAlertId);
}
