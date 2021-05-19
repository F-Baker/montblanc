package montblanc.services;

import org.springframework.stereotype.Service;

/**
 * purpose:
 *   create the email using data pulled from the signup form
 */
@Service
public interface EmailService {
    public void sendEmail(String to, String subject, String template);
}
