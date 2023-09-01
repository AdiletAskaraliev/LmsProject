package adilet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", allocationSize = 1)
    private Long id;
    private String groupName;
    @Column(name = "image_link")
    private String imageLink;
    private String description;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private List<Course> courses;
    @OneToMany(mappedBy = "group",
            cascade = CascadeType.ALL
    )
    private List<Student> students;
}