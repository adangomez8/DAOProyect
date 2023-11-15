package Microservicioadmin.Security.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DTOJWTToken {
    private String idToken;

    public DTOJWTToken(String idToken) {
        this.idToken = idToken;
    }
}
