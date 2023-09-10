package adilet.dto.response;

import adilet.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private Role role;

    public InstructorResponse(Long id, String firstName, String lastName, String phoneNumber, String specialization, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.role = role;
    }
}
