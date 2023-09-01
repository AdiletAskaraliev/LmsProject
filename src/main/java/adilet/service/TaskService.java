package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.TaskRequest;
import adilet.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse save(TaskRequest taskRequest, Long lesId);

    List<TaskResponse> findAllByLessonId(Long lesId);

    TaskResponse findTaskById(Long id);

    SimpleResponse update(Long id, TaskRequest taskRequest);

    SimpleResponse delete(Long id);
}
