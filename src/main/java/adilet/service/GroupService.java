package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.GroupRequest;
import adilet.dto.response.GroupResponse;
import adilet.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAllByCourseId(Long courseId);

    GroupResponse saveToCourse(Long courseId, GroupRequest groupRequest);

    GroupResponse findByCourseId(Long courseId, Long groupId);

    SimpleResponse updateByCourseId(GroupRequest groupRequest, Long groupId);

    SimpleResponse delete(Long id);

    int sumStudentGroup(Long id);
}
