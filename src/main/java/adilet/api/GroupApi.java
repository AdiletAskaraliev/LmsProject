package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.GroupRequest;
import adilet.dto.response.GroupResponse;
import adilet.entity.Group;
import adilet.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupApi {

    private final GroupService groupService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{courseId}")
    public List<Group> getAll(@PathVariable Long courseId){
        return groupService.findAllByCourseId(courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/{courseId}/save")
    public GroupResponse save(@PathVariable Long courseId,
                              @RequestBody GroupRequest groupRequest){
        return groupService.saveToCourse(courseId, groupRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{courseId}/{groupId}")
    public GroupResponse findById(@PathVariable Long courseId,
                                  @PathVariable Long groupId){
        return groupService.findByCourseId(courseId, groupId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{groupId}")
    public SimpleResponse update(@PathVariable Long groupId,
                                 @RequestBody GroupRequest groupRequest){
        return groupService.updateByCourseId(groupRequest, groupId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return groupService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{id}/sum")
    public int sumStudent(@PathVariable Long id){
        return groupService.sumStudentGroup(id);
    }
}
