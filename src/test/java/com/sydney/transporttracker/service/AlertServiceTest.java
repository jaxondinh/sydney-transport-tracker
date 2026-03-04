package com.sydney.transporttracker.service;

import com.sydney.transporttracker.repository.AlertRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {
    @Mock
    private AlertRepository alertRepository;
    @InjectMocks
    private AlertService alertService;
}
