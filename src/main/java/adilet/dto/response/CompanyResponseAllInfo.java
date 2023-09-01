package adilet.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponseAllInfo {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private List<String> courseName;
    private List<String> groupName;
    private List<String> instructorName;
    private int sumStudent;

    public CompanyResponseAllInfo(Long id, String name, String country, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public CompanyResponseAllInfo(Long id, String name, String country, String address, String phoneNumber, List<String> courseName,
                                  List<String> groupName, List<String> instructorName, int sumStudent) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.courseName = courseName;
        this.groupName = groupName;
        this.instructorName = instructorName;
        this.sumStudent = sumStudent;
    }
}
