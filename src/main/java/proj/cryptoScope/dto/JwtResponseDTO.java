package proj.cryptoScope.dto;

import lombok.Data;

@Data
public class JwtResponseDTO {

    private String token;
    private String type = "Bearer";

    public JwtResponseDTO(String token) {
        this.token = token;
    }
}
