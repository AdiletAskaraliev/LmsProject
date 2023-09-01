package adilet.dto.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GroupRequest {
    private String groupName;
    private String imageLink;
    private String description;

    public GroupRequest(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}
