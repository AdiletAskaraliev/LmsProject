package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.StudentRequest;
import adilet.dto.response.StudentResponse;
import adilet.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;


    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @PostMapping("/{groupId}/{studId}")
    public SimpleResponse addToGroup(@PathVariable Long groupId,
                                     @PathVariable Long studId){
        return studentService.addToGroup(groupId, studId);
    }

    @GetMapping("/{studId}")
    public StudentResponse findById(@PathVariable Long studId){
        return studentService.findById(studId);
    }

    @PutMapping("/{studId}")
    public SimpleResponse update(@PathVariable Long studId,
                                 @RequestBody StudentRequest studentRequest){
        return studentService.update(studentRequest, studId);
    }

    @DeleteMapping("/{studId}")
    public SimpleResponse delete(@PathVariable Long studId){
        return studentService.delete(studId);
    }



}
