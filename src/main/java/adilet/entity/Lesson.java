package adilet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", allocationSize = 1)
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;
    @OneToMany(mappedBy = "lesson",
            cascade = CascadeType.ALL
    )
    private List<Task> tasks;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private Course course;

}