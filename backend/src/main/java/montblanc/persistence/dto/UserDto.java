package montblanc.persistence.dto;

import montblanc.persistence.entities.User;
import montblanc.security.SignUpRequest;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private List<String> roles;

    public UserDto() {
        this.roles = new ArrayList<String>();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return "UserDTO [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", roles=" + roles + "]";
    }


}
