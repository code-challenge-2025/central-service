package br.com.dantebasso.central.service.impl;

import br.com.dantebasso.central.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TerminalNotificationServiceImpl implements NotificationService {
    
    @Override
    public void alarm(final String message) {
        log.info("ALARM: {}", message);
    }
    
}
