package adilet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    public CourseResponse(Long id, String courseName, LocalDate dateOfStart, String description) {
        this.id = id;
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}
