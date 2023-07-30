package com.library.management.notification.service;

import com.library.management.notification.config.JmsConfig;
import com.library.management.notification.model.events.LibEvent;
import com.library.management.notification.model.events.NewBookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailService {

    private final MemberServiceRestTemplate restTemplate;
    private final MailSender mailSender;

    @JmsListener(destination = JmsConfig.NEW_BOOK_ADDED_QUEUE)
    public void listen(NewBookEvent newBookEvent){
        log.info("Received newBookEvent for title: " + newBookEvent.getBookDto().getTitle());
        log.debug("Received newBookEvent: " + newBookEvent.getBookDto().toString());

        sendEmailToAll(newBookEvent);
    }

    public void sendEmailToAll(LibEvent libEvent)
    {
        log.info("email message about to be sent: ");
        log.debug(libEvent.toString());

        SimpleMailMessage message = new SimpleMailMessage();
        //List<String> emailAddresses = restTemplate.getEmailAddressAll();
        List<String> emailAddresses = Arrays.asList("najibs1@yahoo.com");

        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(message);

            msg.setTo(emailAddresses.toArray(new String[0]));
            msg.setText(
                    new StringBuilder().append("Dear Member: \n")
                            .append(", We have added a new book to the library\n ")
                            .append(libEvent.getEventDetail())
                            .append("\n\n")
                            .append("Sincerely,\n")
                            .append("Your Librarian")
                            .toString());
            try {
                mailSender.send(msg);
            } catch (MailException ex) {
                log.error(ex.getMessage());
                throw new RuntimeException("problem sending email to: " + emailAddresses);
            }

    }

}
