package adilet.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequest {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

    public CompanyRequest(String name, String country, String address, String phoneNumber) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
