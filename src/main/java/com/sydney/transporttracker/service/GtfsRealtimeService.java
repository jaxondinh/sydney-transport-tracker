package com.sydney.transporttracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GtfsRealtimeService {
    @Value("${tfn.api.key}")
    private String apiKey;
}
