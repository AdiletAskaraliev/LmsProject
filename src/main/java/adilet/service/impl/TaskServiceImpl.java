package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.TaskRequest;
import adilet.dto.response.TaskResponse;
import adilet.entity.Lesson;
import adilet.entity.Task;
import adilet.repository.LessonRepository;
import adilet.repository.TaskRepository;
import adilet.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public TaskResponse save(TaskRequest taskRequest, Long lesId) {
        Lesson lesson = lessonRepository.findById(lesId).orElseThrow(
                () -> new NoSuchElementException("Lesson with id: " + lesId + " not found")
        );

        Task task = new Task();
        task.setLesson(lesson);

        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());

        taskRepository.save(task);

        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText(),
                task.getDeadline()
        );
    }

    @Override
    public List<TaskResponse> findAllByLessonId(Long lesId) {
        return taskRepository.findAllByLessonId(lesId);
    }

    @Override
    public TaskResponse findTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    @Override
    public SimpleResponse update(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Task with id: " + id + " not found")
        );

        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());

        taskRepository.save(task);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success update")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        taskRepository.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success delete")
                .build();
    }
}
