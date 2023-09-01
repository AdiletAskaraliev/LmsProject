package adilet.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CourseRequest {
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    public CourseRequest(String courseName, LocalDate dateOfStart, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}