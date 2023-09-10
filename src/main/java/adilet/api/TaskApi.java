package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.TaskRequest;
import adilet.dto.response.TaskResponse;
import adilet.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskApi {

    private final TaskService taskService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/{lesId}")
    public TaskResponse save(@PathVariable Long lesId,
                              @RequestBody TaskRequest taskRequest ){
        return taskService.save(taskRequest, lesId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{lesId}")
    public List<TaskResponse> getAll(@PathVariable Long lesId){
        return taskService.findAllByLessonId(lesId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{id}/get")
    public TaskResponse findById(@PathVariable Long id){
        return taskService.findTaskById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody TaskRequest taskRequest){
        return taskService.update(id, taskRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return taskService.delete(id);
    }
}
