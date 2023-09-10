package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.GroupRequest;
import adilet.dto.response.GroupResponse;
import adilet.entity.Course;
import adilet.entity.Group;
import adilet.exception.NotFoundException;
import adilet.repository.CourseRepository;
import adilet.repository.GroupRepository;
import adilet.service.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Group> findAllByCourseId(Long courseId) {
        return groupRepository.findAllByCourseId(courseId);
    }

    @Override
    public GroupResponse saveToCourse(Long courseId, GroupRequest groupRequest) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> {
                    log.error("Course with id: " + courseId + " not found");
                    return new NotFoundException("Course with id: " + courseId + " not found");
                });
        Group group = new Group();
        group.setCourses(new ArrayList<>());
        group.getCourses().add(course);

        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());


        groupRepository.save(group);
        log.info("Successfully saved");
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                groupRequest.getDescription()
        );
    }

    @Override
    public GroupResponse findByCourseId(Long courseId, Long groupId) {
        return groupRepository.findByCourseId(courseId, groupId)
                .orElseThrow(() -> {
                    log.error("Course with id: " + courseId + " not found");
                    return new NotFoundException("Course with id: " + courseId + " not found");
                });
    }

    @Override
    public SimpleResponse updateByCourseId(GroupRequest groupRequest, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    log.error("Group with id: " + groupId + " not found");
                    return new NotFoundException("Group with id: " + groupId + " not found");
                });

        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());

        groupRepository.save(group);
        log.info("Successfully update");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        groupRepository.deleteById(id);
        log.info("Successfully delete!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted!")
                .build();
    }

    @Override
    public int sumStudentGroup(Long id) {
        return groupRepository.sumStudentGroup(id);
    }


}
