package proj.cryptoScope.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private boolean active;
    private List<String> favoriteTokens;

    public UserDTO(String username, String email, String role, boolean active, List<String> favoriteTokens) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.active = active;
        this.favoriteTokens = favoriteTokens;
    }
}
