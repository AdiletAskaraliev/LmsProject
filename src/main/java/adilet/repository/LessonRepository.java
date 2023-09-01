package adilet.repository;

import adilet.dto.response.LessonResponse;
import adilet.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select new adilet.dto.response.LessonResponse(l.id, l.lessonName) " +
            "from Lesson l join l.course c where c.id = :courseId")
    List<LessonResponse> findAllByCourseId(Long courseId);

    @Query("select new adilet.dto.response.LessonResponse(l.id, l.lessonName)" +
            " from Lesson l where l.id = :id")
    Optional<LessonResponse> findLessonById(Long id);
}
