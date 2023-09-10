package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.StudentRequest;
import adilet.dto.response.StudentResponse;
import adilet.entity.Group;
import adilet.entity.Student;
import adilet.enums.StudyFormat;
import adilet.exception.NotFoundException;
import adilet.repository.GroupRepository;
import adilet.repository.StudentRepository;
import adilet.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
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
        student.setRole(studentRequest.getRole());

        studentRepository.save(student);
        log.info("Successfully saved");
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getRole()
        );
    }

    @Override
    public List<StudentResponse> getAll() {
        return studentRepository.getAll();

    }

    @Override
    public SimpleResponse addToGroup(Long groupId, Long studId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    log.error("Group with id: " + groupId + " not found");
                    return new NotFoundException("Group with id: " + groupId + " not found");
                });
        Student student = studentRepository.findById(studId)
                .orElseThrow(() -> {
                    log.error("Student with id: " + studId + " not found");
                    return new NotFoundException("Student with id: " + studId + " not found");
                });
        student.setGroup(group);
        studentRepository.save(student);
        log.info("Successfully added!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully added!")
                .build();
    }

    @Override
    public StudentResponse findById(Long studId) {
        return studentRepository.findStudentById(studId)
                .orElseThrow(() -> {
                    log.error("Student with id: " + studId + " not found");
                    return new NotFoundException("Student with id: " + studId + " not found");
                });
    }

    @Override
    public SimpleResponse update(StudentRequest studentRequest, Long studId) {
        Student student = studentRepository.findById(studId)
                .orElseThrow(() -> {
                    log.error("Student with id: " + studId + " not found");
                    return new NotFoundException("Student with id: " + studId + " not found");
                });

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());

        log.info("Successfully update!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update!")
                .build();
    }

    @Override
    public SimpleResponse delete(Long studId) {
        studentRepository.deleteById(studId);
        log.info("Successfully deleted!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted!")
                .build();
    }

    @Override
    public List<StudentResponse> filterStudentByStudyFormat(Long comId, StudyFormat studyFormat) {
        return studentRepository.findByCompanyAndStudyFormat(comId, studyFormat);
    }

    @Override
    public SimpleResponse blockStudent(Long studentId, Boolean isBlock) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    log.error("Student with id: " + studentId + " not found");
                    return new NotFoundException("Student with id: " + studentId + " not found");
                });
        student.setIsBlocked(true);
        log.info("Student is blocked...");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is blocked...")
                .build();
    }

    @Override
    public SimpleResponse unBlockStudent(Long studentId, Boolean anBlock) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    log.error("Student with id: " + studentId + " not found");
                    return new NotFoundException("Student with id: " + studentId + " not found");
                });
        student.setIsBlocked(false);
        log.info("Student is unblocked...");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Student is unblocked...")
                .build();
    }
}
