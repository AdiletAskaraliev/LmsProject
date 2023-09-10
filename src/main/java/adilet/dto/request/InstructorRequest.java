package adilet.dto.request;

import adilet.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private Role role;

    public InstructorRequest(String firstName, String lastName, String phoneNumber, String specialization, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.role = role;
    }
}
