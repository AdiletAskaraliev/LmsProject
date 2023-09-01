package adilet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequest {
    private String taskName;
    private String taskText;
    private LocalDateTime deadline;

    public TaskRequest(String taskName, String taskText, LocalDateTime deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
}
