package s3_gps_ivanti.business.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
public class CreatorNotFoundException extends ResponseStatusException {
    public CreatorNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "CREATOR_NOT_FOUND");
    }
}