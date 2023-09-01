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
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String specialization;
    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private List<Company> companies;
    @OneToMany(mappedBy = "instructor",
            cascade = CascadeType.ALL
    )
    private List<Course> courses;

    public void addCompany(Company company){
        if (companies == null){
            companies = new ArrayList<>();
        } else companies.add(company);
    }

}