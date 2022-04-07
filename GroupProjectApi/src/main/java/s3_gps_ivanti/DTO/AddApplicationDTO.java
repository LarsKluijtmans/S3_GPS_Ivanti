package s3_gps_ivanti.DTO;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class AddApplicationDTO {

    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private ArrayList<String> images;
    @NonNull
    private String icon;

    public AddApplicationDTO(String title, String description, ArrayList<String> images, String icon)
    {
        this.title = title;
        this.description = description;
        this.images = images;
        this.icon = icon;
    }
}
