package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.StudentRequest;
import adilet.dto.response.StudentResponse;
import adilet.entity.Group;
import adilet.entity.Student;
import adilet.repository.GroupRepository;
import adilet.repository.StudentRepository;
import adilet.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public StudentResponse save(StudentRequest studentRequest) {
        Student student = new Student();

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());

        studentRepository.save(student);

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat()
        );
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentRepository.getAll();

    }

    @Override
    public SimpleResponse addToGroup(Long groupId, Long studId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new NoSuchElementException("Group with id: " + groupId + " not found")
        );
        Student student = studentRepository.findById(studId).orElseThrow(
                () -> new NoSuchElementException("Student with id: " + studId + " not found")
        );
        student.setGroup(group);
        studentRepository.save(student);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully added!")
                .build();
    }

    @Override
    public StudentResponse findById(Long studId) {
        return studentRepository.findStudentById(studId).orElseThrow(
                () -> new NoSuchElementException("Student with id: " + studId + " not found")
        );
    }

    @Override
    public SimpleResponse update(StudentRequest studentRequest, Long studId) {
        Student student = studentRepository.findById(studId).orElseThrow(
                () -> new NoSuchElementException("Student with id: " + studId + " not found")
        );

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());


        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update!")
                .build();
    }

    @Override
    public SimpleResponse delete(Long studId) {
        studentRepository.deleteById(studId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted!")
                .build();
    }
}
