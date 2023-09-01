package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.InstructorRequest;
import adilet.dto.response.InstructorResponse;
import adilet.dto.response.InstructorResponseInfo;
import adilet.entity.Company;
import adilet.entity.Course;
import adilet.entity.Instructor;
import adilet.repository.CompanyRepository;
import adilet.repository.CourseRepository;
import adilet.repository.InstructorRepository;
import adilet.service.InstructorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public InstructorResponse save(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();

        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        instructorRepository.save(instructor);
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getPhoneNumber(),
                instructor.getSpecialization()
        );
    }

    @Override
    public InstructorResponse findById(Long id) {
        return instructorRepository.findInstructorById(id).orElseThrow(
                () -> new NoSuchElementException("Instructor with id: " + id + " not found")
        );
    }

    @Override
    public SimpleResponse update(Long id, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Instructor with id: " + id + " not found")
        );
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());

        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update!")
                .build();
    }

    @Override
    public SimpleResponse assignToCompany(Long comId, Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Instructor with id: " + id + " not found")
        );
        Company company = companyRepository.findById(comId).orElseThrow(
                () -> new NoSuchElementException("Company with id: " + comId + " not found")
        );
        instructor.getCompanies().add(company);
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully assigned!")
                .build();
    }

    @Override
    public SimpleResponse assignToCourse(Long id, Long courseId) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Instructor with id: " + id + " not found")
        );
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new NoSuchElementException("Course with id: " + courseId + " not found")
        );

        course.setInstructor(instructor);
        courseRepository.save(course);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully assigned!")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        instructorRepository.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted!")
                .build();
    }

    @Override
    public int sumStudent(Long id) {
        return instructorRepository.sumStudent(id);
    }

    @Override
    public InstructorResponseInfo instructorInfo(Long id) {
        InstructorResponseInfo instructorInfo = instructorRepository.instructorInfo(id);

        instructorInfo.setGroupName(instructorRepository.groupName(id));
        instructorInfo.setSumStudent(instructorRepository.sumStudent(id));
        return new InstructorResponseInfo(
                instructorInfo.getId(),
                instructorInfo.getFirstName(),
                instructorInfo.getLastName(),
                instructorInfo.getPhoneNumber(),
                instructorInfo.getSpecialization(),
                instructorInfo.getGroupName(),
                instructorInfo.getSumStudent()
        );
    }
}
