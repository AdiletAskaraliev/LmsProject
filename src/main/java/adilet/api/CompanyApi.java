package adilet.api;

import adilet.dto.SimpleResponse;
import adilet.dto.request.CompanyRequest;
import adilet.dto.response.CompanyResponse;
import adilet.dto.response.CompanyResponseAllInfo;
import adilet.entity.Company;
import adilet.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @GetMapping
    public List<Company> getAll(){
        return companyService.findAll();
    }

    @PostMapping
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest){
        return companyService.save(companyRequest);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getById(@PathVariable Long companyId){
        return companyService.findById(companyId);
    }

    @PutMapping("/{companyId}")
    public SimpleResponse update(@PathVariable Long companyId,
                                 @RequestBody CompanyRequest companyRequest){
        return companyService.update(companyId, companyRequest);
    }

    @DeleteMapping("/{companyId}")
    public SimpleResponse delete(@PathVariable Long companyId){
        return companyService.delete(companyId);
    }

    @GetMapping("/{id}/info")
    public CompanyResponseAllInfo callCompany(@PathVariable Long id){
        return companyService.companyInfo(id);
    }
}
