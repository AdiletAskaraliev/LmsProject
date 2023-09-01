package adilet.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {
    private String lessonName;

    public LessonRequest() {
    }

    public LessonRequest(String lessonName) {
        this.lessonName = lessonName;
    }
}
