package adilet.dto.request;

import adilet.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private Role role;

    public SignUpRequest(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
