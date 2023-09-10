package adilet.service.impl;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CompanyRequest;
import adilet.dto.response.CompanyResponse;
import adilet.dto.response.CompanyResponseAllInfo;
import adilet.entity.Company;
import adilet.exception.AlreadyExistException;
import adilet.exception.NotFoundException;
import adilet.repository.CompanyRepository;
import adilet.service.CompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;


    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }


    @Override
    public CompanyResponse save(CompanyRequest companyRequest) {
        Company company = new Company();


        if (companyRepository.existsByName(companyRequest.getName())) {

            throw new AlreadyExistException("Company with name " + companyRequest.getName() + " already exists");
        }


        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());

        log.info("Company successfully saved");
        companyRepository.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber()
        );
    }

    @Override
    public CompanyResponse findById(Long companyId) {
        return companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> {
                    log.error("Company with id: " + companyId + " not found");
                    return new NotFoundException("Company with id: " + companyId + " not found");
                });
    }

    @Override
    public SimpleResponse update(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> {
                    log.error("Company with id: " + companyId + " not found");
                    return new NotFoundException("Company with id: " + companyId + " not found");
                });
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());

        log.info("Company successfully updated");
        companyRepository.save(company);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully update")
                .build();
    }

    @Override
    public SimpleResponse delete(Long companyId) {
        companyRepository.deleteById(companyId);

        log.info("Company successfully deleted!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted!")
                .build();
    }

    @Override
    public CompanyResponseAllInfo companyInfo(Long id) {
        CompanyResponseAllInfo companyResponseAllInfo = companyRepository.companyInfo(id);

        List<String> courseName = companyRepository.courseName(id);
        List<String> groupName = companyRepository.groupName(id);
        List<String> instructorName = companyRepository.instructorName(id);
        int sumStudent = companyRepository.sumStudent(id);

        companyResponseAllInfo.setCourseName(courseName);
        companyResponseAllInfo.setGroupName(groupName);
        companyResponseAllInfo.setInstructorName(instructorName);
        companyResponseAllInfo.setSumStudent(sumStudent);
        log.info("Company info successfully received");
        return new CompanyResponseAllInfo(
                companyResponseAllInfo.getId(),
                companyResponseAllInfo.getName(),
                companyResponseAllInfo.getCountry(),
                companyResponseAllInfo.getAddress(),
                companyResponseAllInfo.getPhoneNumber(),
                companyResponseAllInfo.getCourseName(),
                companyResponseAllInfo.getGroupName(),
                companyResponseAllInfo.getInstructorName(),
                companyResponseAllInfo.getSumStudent()
        );
    }
}
