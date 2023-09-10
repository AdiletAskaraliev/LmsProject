package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.StudentRequest;
import adilet.dto.response.StudentResponse;
import adilet.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    StudentResponse save(StudentRequest studentRequest);

    List<StudentResponse> getAll();

    SimpleResponse addToGroup(Long groupId, Long studId);

    StudentResponse findById(Long studId);

    SimpleResponse update(StudentRequest studentRequest, Long studId);

    SimpleResponse delete(Long studId);

    List<StudentResponse> filterStudentByStudyFormat(Long comId, StudyFormat studyFormat);

    SimpleResponse blockStudent(Long studentId, Boolean isBlocked);

    SimpleResponse unBlockStudent(Long studentId, Boolean anBlocked);
}
