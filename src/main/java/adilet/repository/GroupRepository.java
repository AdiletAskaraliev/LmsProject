package adilet.repository;

import adilet.dto.response.GroupResponse;
import adilet.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select g from Group g join g.courses c where c.id = :courseId")
    List<Group> findAllByCourseId(Long courseId);

    @Query("select g from Group g join g.courses c where c.id = :courseId and g.id = :groupId")
    Optional<GroupResponse> findByCourseId(Long courseId, Long groupId);


    @Query("select count(s) from Student s where s.group.id = :id")
    int sumStudentGroup(Long id);
}
