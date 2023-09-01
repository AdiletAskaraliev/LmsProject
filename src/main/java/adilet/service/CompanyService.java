package adilet.service;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CompanyRequest;
import adilet.dto.response.CompanyResponse;
import adilet.dto.response.CompanyResponseAllInfo;
import adilet.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    CompanyResponse save(CompanyRequest companyRequest);

    CompanyResponse findById(Long companyId);

    SimpleResponse update(Long companyId, CompanyRequest companyRequest);

    SimpleResponse delete(Long companyId);

    CompanyResponseAllInfo companyInfo(Long id);
}
