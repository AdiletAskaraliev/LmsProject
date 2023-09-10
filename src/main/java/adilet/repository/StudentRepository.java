package adilet.repository;

import adilet.dto.response.StudentResponse;
import adilet.entity.Student;
import adilet.enums.StudyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new adilet.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.phoneNumber, s.email,s.studyFormat) " +
            "from Student s")
    List<StudentResponse> getAll();

    @Query("select new adilet.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.phoneNumber, s.email,s.studyFormat)" +
            "from Student s where s.id = :studId")
    Optional<StudentResponse> findStudentById(Long studId);

    @Query("select new adilet.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.phoneNumber, s.email,s.studyFormat)" +
            " from Student s join s.group g join g.courses c where c.company.id = :comId and s.studyFormat = :studyFormat")
    List<StudentResponse> findByCompanyAndStudyFormat(Long comId, StudyFormat studyFormat);

    @Modifying
    @Query("update Student s set s.isBlocked = :isBlocked where s.id = :studentId")
    int blockStudent(@Param("studentId") Long studentId, @Param("isBlocked") Boolean isBlocked);

}
