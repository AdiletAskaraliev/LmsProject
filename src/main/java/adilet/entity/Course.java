package adilet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "date_of_start")
    private LocalDate dateOfStart;
    private String description;
    @ToString.Exclude
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private Company company;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private Instructor instructor;
    @ManyToMany(mappedBy = "courses",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH
            }
    )
    private List<Group> groups;
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private List<Lesson> lessons;

}