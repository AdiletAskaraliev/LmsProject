package adilet.repository;

import adilet.dto.response.TaskResponse;
import adilet.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select new adilet.dto.response.TaskResponse(t.id, t.taskName, t.taskText, t.deadline) " +
            "from Task t where t.lesson.id = :lesId")
    List<TaskResponse> findAllByLessonId(Long lesId);

    @Query("select  new adilet.dto.response.TaskResponse(t.id, t.taskName, t.taskText, t.deadline)" +
            "from Task t where t.id = :id")
    TaskResponse findTaskById(Long id);
}
