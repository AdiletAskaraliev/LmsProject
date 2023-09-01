package adilet.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;

    public InstructorRequest(String firstName, String lastName, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
