package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.StudentRequest;
import adilet.dto.response.StudentResponse;
import adilet.enums.StudyFormat;
import adilet.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/{groupId}/{studId}")
    public SimpleResponse addToGroup(@PathVariable Long groupId,
                                     @PathVariable Long studId){
        return studentService.addToGroup(groupId, studId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{studId}")
    public StudentResponse findById(@PathVariable Long studId){
        return studentService.findById(studId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{studId}")
    public SimpleResponse update(@PathVariable Long studId,
                                 @RequestBody StudentRequest studentRequest){
        return studentService.update(studentRequest, studId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{studId}")
    public SimpleResponse delete(@PathVariable Long studId){
        return studentService.delete(studId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/filter/{comId}")
    public List<StudentResponse> filterStudent(@PathVariable Long comId,
                                               @RequestParam StudyFormat studyFormat){
        List<StudentResponse> studentResponses = studentService.filterStudentByStudyFormat(comId, studyFormat);
        return studentResponses;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/blocked/{studentId}")
    public SimpleResponse block(@PathVariable Long studentId, Boolean isBlocked) {
        studentService.blockStudent(studentId, isBlocked);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is blocked...")
                .build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/unBlocked/{studentId}")
    public SimpleResponse unBlock(@PathVariable Long studentId, Boolean anBlocked) {
        studentService.unBlockStudent(studentId, anBlocked);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is unblocked...")
                .build();
    }

}
