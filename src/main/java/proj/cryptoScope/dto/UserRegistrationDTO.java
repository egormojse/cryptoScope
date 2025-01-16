package proj.cryptoScope.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String username;
    private String email;
    private String password;

    public UserRegistrationDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
