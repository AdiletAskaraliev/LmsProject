package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CourseRequest;
import adilet.dto.response.CourseResponse;
import adilet.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{comId}")
    public List<CourseResponse> getAll(@PathVariable Long comId) {
        return courseService.findAllByCompanyId(comId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{comId}")
    public CourseResponse save(@PathVariable Long comId,
                               @RequestBody CourseRequest courseRequest) {
        return courseService.saveToCompany(courseRequest, comId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{comId}/{courseId}")
    public CourseResponse getById(@PathVariable Long comId,
                                  @PathVariable Long courseId) {
        return courseService.findByIdWithCompanyId(comId, courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{comId}/{courseId}")
    public SimpleResponse update(@PathVariable Long comId,
                                 @PathVariable Long courseId,
                                 @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseByCompanyId(comId, courseId, courseRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{comId}/{courseId}")
    public SimpleResponse delete(@PathVariable Long comId,
                                 @PathVariable Long courseId) {
        return courseService.deleteCourseByCompanyId(comId, courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/sort/{companyId}")
    public List<CourseResponse> sortCoursesByDate(@PathVariable Long companyId) {
       return courseService.sortCourse(companyId);
    }

}
