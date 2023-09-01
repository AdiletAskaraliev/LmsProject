package adilet.repository;

import adilet.dto.response.CompanyResponse;
import adilet.dto.response.CompanyResponseAllInfo;
import adilet.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<CompanyResponse> findCompanyById(Long companyId);

    @Query("select case when count (c) > 0 then true else false end from Company c where c.name = :name")
    boolean existsByName(String name);

    @Query("select new adilet.dto.response.CompanyResponseAllInfo(" +
            "c.id, c.name, c.country, c.address, c.phoneNumber) " +
            "from Company c where c.id = :id")
    CompanyResponseAllInfo companyInfo(Long id);

    @Query("select count(s) from Company c join c.courses course join course.groups g join g.students s where c.id = :id")
    int sumStudent(Long id);

    @Query("select course.courseName from Company c join c.courses course where c.id = :id")
    List<String> courseName(Long id);

    @Query("select g.groupName from Company c join c.courses course join course.groups g where c.id = :id")
    List<String> groupName(Long id);

    @Query("select concat(i.firstName, ' ', i.lastName) from Company c join c.instructor i where c.id = :id")
    List<String> instructorName(Long id);


}
