package br.com.dantebasso.central.service;

import br.com.dantebasso.central.service.impl.TerminalNotificationServiceImpl;
import org.junit.jupiter.api.Test;

public class TerminalNotificationServiceImplUnitTest {

    @Test
    public void testAlarmShouldNotThrowsException() {
        final NotificationService notificationService = new TerminalNotificationServiceImpl();
        notificationService.alarm("Test");
    }

}
