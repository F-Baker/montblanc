package montblanc.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

//@Entity
public class Email {

//    @Id
//    private Long emailId;
    private String template;
    private String attachmentName;
    private String attachmentPath;

    private String to;
    private String subject;
    private Map<String, Object> model;

    public Email() {
    }

    public Email(String template, String attachmentName, String attachmentPath, String to, String subject, Map<String, Object> model) {
        this.template = template;
        this.attachmentName = attachmentName;
        this.attachmentPath = attachmentPath;
        this.to = to;
        this.subject = subject;
        this.model = model;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}