package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CourseRequest;
import adilet.dto.response.CourseResponse;
import adilet.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @GetMapping("/{comId}")
    public List<CourseResponse> getAll(@PathVariable Long comId) {
        return courseService.findAllByCompanyId(comId);
    }

    @PostMapping("/{comId}")
    public CourseResponse save(@PathVariable Long comId,
                               @RequestBody CourseRequest courseRequest) {
        return courseService.saveToCompany(courseRequest, comId);
    }

    @GetMapping("/{comId}/{courseId}")
    public CourseResponse getById(@PathVariable Long comId,
                                  @PathVariable Long courseId) {
        return courseService.findByIdWithCompanyId(comId, courseId);
    }

    @PutMapping("/{comId}/{courseId}")
    public SimpleResponse update(@PathVariable Long comId,
                                 @PathVariable Long courseId,
                                 @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseByCompanyId(comId, courseId, courseRequest);
    }

    @DeleteMapping("/{comId}/{courseId}")
    public SimpleResponse delete(@PathVariable Long comId,
                                 @PathVariable Long courseId) {
        return courseService.deleteCourseByCompanyId(comId, courseId);
    }

    @GetMapping("/sort/{companyId}")
    public List<CourseResponse> sortCoursesByDate(@PathVariable Long companyId) {
       return courseService.sortCourse(companyId);
    }

}
