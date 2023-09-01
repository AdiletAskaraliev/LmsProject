package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.LessonRequest;
import adilet.dto.response.LessonResponse;
import adilet.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("/{courseId}")
    public LessonResponse save(@RequestBody LessonRequest lessonRequest,
                               @PathVariable Long courseId){
        return lessonService.save(lessonRequest, courseId);
    }

    @GetMapping("/{courseId}")
    public List<LessonResponse> getAll(@PathVariable Long courseId){
        return lessonService.getAllByCourseId(courseId);
    }

    @GetMapping("/{id}/get")
    public LessonResponse findById(@PathVariable Long id){
        return lessonService.findById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody LessonRequest lessonRequest){
        return lessonService.update(id, lessonRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return lessonService.delete(id);
    }
}
