package com.library.management.notification.service.listeners;

import com.library.management.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewBookAddedListener {

    private final EmailService emailService;
}