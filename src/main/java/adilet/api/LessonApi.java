package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.LessonRequest;
import adilet.dto.response.LessonResponse;
import adilet.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonApi {

    private final LessonService lessonService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/{courseId}")
    public LessonResponse save(@RequestBody LessonRequest lessonRequest,
                               @PathVariable Long courseId){
        return lessonService.save(lessonRequest, courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{courseId}")
    public List<LessonResponse> getAll(@PathVariable Long courseId){
        return lessonService.getAllByCourseId(courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{id}/get")
    public LessonResponse findById(@PathVariable Long id){
        return lessonService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody LessonRequest lessonRequest){
        return lessonService.update(id, lessonRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return lessonService.delete(id);
    }
}
