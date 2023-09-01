package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.LessonRequest;
import adilet.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {
    LessonResponse save(LessonRequest lessonRequest, Long courseId);

    List<LessonResponse> getAllByCourseId(Long courseId);

    LessonResponse findById(Long id);

    SimpleResponse update(Long id, LessonRequest lessonRequest);

    SimpleResponse delete(Long id);
}
