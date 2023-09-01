package adilet.repository;

import adilet.dto.response.InstructorResponse;
import adilet.dto.response.InstructorResponseInfo;
import adilet.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {


    Optional<InstructorResponse> findInstructorById(Long id);

    @Query("select count(s) from Student s " +
            "join s.group g " +
            "join g.courses c " +
            "join c.instructor i where i.id= :id")
    int sumStudent(Long id);

    @Query("select new adilet.dto.response.InstructorResponseInfo(i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization) " +
            "from Instructor i where i.id = :id")
    InstructorResponseInfo instructorInfo(Long id);

    @Query("select g.groupName from Instructor i join i.courses c join c.groups g where i.id = :id")
    List<String> groupName(Long id);

}
