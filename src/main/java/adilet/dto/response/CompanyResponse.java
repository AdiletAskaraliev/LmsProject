package adilet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponse{
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

    public CompanyResponse(Long id, String name, String country, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
