package montblanc.service;

import montblanc.dto.UserDTO;

public class JWTResult {

    private final String login;
    private final UserDTO userDTO;
    private final boolean ok;

    private JWTResult(String login, UserDTO userDTO, boolean ok) {
        this.login = login;
        this.userDTO = userDTO;
        this.ok = ok;
    }

    public static JWTResult buildFail() {
        return new JWTResult(null, null, false);
    }

    public static JWTResult buildInfo(String login, UserDTO userDTO) {
        return new JWTResult(login, userDTO, true);
    }

    public String getLogin() {
        return login;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isEmploye() {
        return userDTO.getRoles().contains("ROLE_EMPLOYE");
    }

    public boolean isUser() {
        return userDTO.getRoles().contains("ROLE_USER");
    }
}