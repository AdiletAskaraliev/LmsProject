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
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", allocationSize = 1, initialValue = 2)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String country;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ToString.Exclude
    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL
    )
    private List<Course> courses;
    @ManyToMany(mappedBy = "companies",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH
            }
    )
    private List<Instructor> instructor;

    public void setCourse(Course course) {
        if (courses == null) courses = new ArrayList<>();
         else courses.add(course);
    }
}