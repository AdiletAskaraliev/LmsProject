package adilet.repository;

import adilet.dto.response.CourseResponse;
import adilet.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new adilet.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart,c.description)" +
            " from Course c where c.company.id = :id")
    List<CourseResponse> findAllByCompanyId(Long id);

    @Query("select c from Course c where c.company.id = :comId and c.id = :courseId")
    Optional<Course> findByIdAndCompanyId(Long comId, Long courseId);

    @Query("select new adilet.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart,c.description)" +
            " from Course c where c.company.id = :comId and c.id = :courseId")
    Optional<CourseResponse> findByIdWithCompanyId(Long comId, Long courseId);

    @Query("SELECT new adilet.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart,c.description)" +
            " FROM Course c WHERE c.company.id = :id ORDER BY c.dateOfStart")
    List<CourseResponse> sortCoursesByDate(@Param("id") Long id);

}
