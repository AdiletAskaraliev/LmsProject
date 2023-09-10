package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.LessonRequest;
import adilet.dto.response.LessonResponse;
import adilet.entity.Course;
import adilet.entity.Lesson;
import adilet.exception.NotFoundException;
import adilet.repository.CourseRepository;
import adilet.repository.LessonRepository;
import adilet.service.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public LessonResponse save(LessonRequest lessonRequest, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id: " + courseId + " not found");
                    return new NotFoundException("Course with id: " + courseId + " not found")
                });
        Lesson lesson = new Lesson();
        lesson.setCourse(course);

        lesson.setLessonName(lessonRequest.getLessonName());
        lessonRepository.save(lesson);
        log.info("Successfully saved");
        return new LessonResponse(
                lesson.getId(),
                lesson.getLessonName()
        );
    }

    @Override
    public List<LessonResponse> getAllByCourseId(Long courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }

    @Override
    public LessonResponse findById(Long id) {
        return lessonRepository.findLessonById(id)
                .orElseThrow(() -> {
                    log.error("Lesson with id: " + id + " not found");
                    return new NotFoundException("Lesson with id: " + id + " not found");
                });
    }

    @Override
    public SimpleResponse update(Long id, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Lesson with id: " + id + " not found");
                    return new NotFoundException("Lesson with id: " + id + " not found");
                });
        lesson.setLessonName(lessonRequest.getLessonName());

        lessonRepository.save(lesson);
        log.info("Successfully update");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        lessonRepository.deleteById(id);
        log.info("Successfully deleted");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted")
                .build();
    }
}
