package montblanc.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendEmail(String to, String subject, String template);
}
