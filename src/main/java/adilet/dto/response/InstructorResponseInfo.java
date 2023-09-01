package adilet.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InstructorResponseInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private List<String> groupName;
    private int sumStudent;

    public InstructorResponseInfo(Long id, String firstName, String lastName, String phoneNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    public InstructorResponseInfo(Long id, String firstName, String lastName, String phoneNumber, String specialization, List<String> groupName, int sumStudent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.groupName = groupName;
        this.sumStudent = sumStudent;
    }
}
