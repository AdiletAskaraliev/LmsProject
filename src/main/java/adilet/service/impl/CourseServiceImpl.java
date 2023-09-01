package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CourseRequest;
import adilet.dto.response.CourseResponse;
import adilet.entity.Company;
import adilet.entity.Course;
import adilet.repository.CompanyRepository;
import adilet.repository.CourseRepository;
import adilet.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<CourseResponse> findAllByCompanyId(Long comId) {
        return courseRepository.findAllByCompanyId(comId);
    }

    @Override
    public CourseResponse saveToCompany(CourseRequest courseRequest, Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setCompany(company);


        courseRepository.save(course);
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDateOfStart(),
                course.getDescription()
        );
    }

    @Override
    public CourseResponse findByIdWithCompanyId(Long comId, Long courseId) {
       return courseRepository.findByIdWithCompanyId(comId, courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id: " + courseId + " not found")
        );
    }

    @Override
    public SimpleResponse updateCourseByCompanyId(Long comId, Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findByIdAndCompanyId(comId, courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id: " + courseId + " not found")
        );
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());

        courseRepository.save(course);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update")
                .build();
    }

    @Override
    public SimpleResponse deleteCourseByCompanyId(Long comId, Long courseId) {
        Course course = courseRepository.findByIdAndCompanyId(comId, courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id: " + courseId + " not found")
        );
        courseRepository.delete(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully delete!")
                .build();
    }

    @Override
    public List<CourseResponse> sortCourse(Long id) {
        return courseRepository.sortCoursesByDate(id);
    }
}
