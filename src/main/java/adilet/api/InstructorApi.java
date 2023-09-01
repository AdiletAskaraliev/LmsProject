package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.InstructorRequest;
import adilet.dto.response.InstructorResponse;
import adilet.dto.response.InstructorResponseInfo;
import adilet.entity.Instructor;
import adilet.service.CompanyService;
import adilet.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorApi {

    private final InstructorService instructorService;
    private final CompanyService companyService;

    @GetMapping()
    public List<Instructor> getAll(){
        return instructorService.findAll();
    }

    @PostMapping
    public InstructorResponse save(@RequestBody InstructorRequest instructorRequest){
        return instructorService.save(instructorRequest);
    }

    @PostMapping("/{comId}/assignToCompany/{id}")
    public SimpleResponse assignToCompany(@PathVariable Long comId,
                                          @PathVariable Long id){
        return instructorService.assignToCompany(comId, id);
    }

    @PostMapping("/{id}/assignToCourse/{courseId}")
    public SimpleResponse assignToCourse(@PathVariable Long id,
                                         @PathVariable Long courseId){
        return instructorService.assignToCourse(id, courseId);
    }


    @GetMapping("/{id}")
    public InstructorResponse findById(@PathVariable Long id){
        return instructorService.findById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody InstructorRequest instructorRequest){
        return instructorService.update(id, instructorRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return instructorService.delete(id);
    }

    @GetMapping("/{id}/sum")
    public int sumStudent(@PathVariable Long id){
        return instructorService.sumStudent(id);
    }

    @GetMapping("/{id}/info")
    public InstructorResponseInfo instructorInfo(@PathVariable Long id){
        return instructorService.instructorInfo(id);
    }


}
