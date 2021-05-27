package montblanc.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequest {
    @Email(message = "error.email.not-valid")
    private String email;
    @NotBlank(message = "error.password.required")
    private String password;
    @NotBlank(message = "error.firstname.required")
    private String firstname;
    @NotBlank(message = "error.lastname.required")
    private String lastname;

    private String serverName = "localhost";
    private String serverPort = "8080";

    public SignUpRequest() {
    }

    public SignUpRequest(String email, String password, String firstname, String lastname, String serverName, String serverPort) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
