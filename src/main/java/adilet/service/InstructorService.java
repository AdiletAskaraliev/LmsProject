package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.InstructorRequest;
import adilet.dto.response.InstructorResponse;
import adilet.dto.response.InstructorResponseInfo;
import adilet.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> findAll();

    InstructorResponse save(InstructorRequest instructorRequest);

    InstructorResponse findById(Long id);

    SimpleResponse update(Long id, InstructorRequest instructorRequest);

    SimpleResponse assignToCompany(Long comId, Long id);

    SimpleResponse assignToCourse(Long id, Long courseId);

    SimpleResponse delete(Long id);

    int sumStudent(Long id);

    InstructorResponseInfo instructorInfo(Long id);
}
