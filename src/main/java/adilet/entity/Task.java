package adilet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", allocationSize = 1)
    private Long id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_text")
    private String taskText;
    private LocalDateTime deadline;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private Lesson lesson;

}