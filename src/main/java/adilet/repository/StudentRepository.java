package adilet.repository;

import adilet.dto.response.StudentResponse;
import adilet.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new adilet.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.phoneNumber, s.email,s.studyFormat) " +
            "from Student s")
    List<StudentResponse> getAll();

    @Query("select new adilet.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.phoneNumber, s.email,s.studyFormat)" +
            "from Student s where s.id = :studId")
    Optional<StudentResponse> findStudentById(Long studId);
}
