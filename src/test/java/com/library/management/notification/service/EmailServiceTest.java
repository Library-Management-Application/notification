package com.library.management.notification.service;

import com.library.management.notification.model.AuthorDto;
import com.library.management.notification.model.BookDto;
import com.library.management.notification.model.events.LibEvent;
import com.library.management.notification.model.events.NewBookEvent;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled // utility for manual testing
@SpringBootTest

class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    void sendEmailToAll() {

        final AuthorDto authorDto = AuthorDto.builder().name("Herman Melville").build();
        final BookDto bookDto =
        BookDto.builder().title("Moby Dick")
                .author(authorDto).build();

        LibEvent libEvent = new NewBookEvent(bookDto);
        emailService.sendEmailToAll(libEvent);
    }
}