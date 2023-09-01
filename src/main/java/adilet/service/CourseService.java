package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CourseRequest;
import adilet.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> findAllByCompanyId(Long comId);

    CourseResponse saveToCompany(CourseRequest courseRequest, Long id);

    CourseResponse findByIdWithCompanyId(Long comId, Long courseId);

    SimpleResponse updateCourseByCompanyId(Long comId, Long courseId, CourseRequest courseRequest);

    SimpleResponse deleteCourseByCompanyId(Long comId, Long courseId);

    List<CourseResponse> sortCourse(Long id);
}
