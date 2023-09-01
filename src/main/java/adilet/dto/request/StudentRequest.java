package adilet.dto.request;

import adilet.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;

    public StudentRequest(String firstName, String lastName, String phoneNumber, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}
