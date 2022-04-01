package S3_GPS_Ivanti.controller;

<<<<<<< HEAD:GroupProjectApi/src/main/java/S3_GPS_Ivanti/controller/ResponseController.java
import S3_GPS_Ivanti.business.ResponseService;
import S3_GPS_Ivanti.business.UserService;
import S3_GPS_Ivanti.model.Response;
import S3_GPS_Ivanti.model.User;
=======
import com.example.S3_GPS_Ivanti.business.ResponseService;

import com.example.S3_GPS_Ivanti.model.*;
>>>>>>> 75208ff8c276fbe9838fb7b8e36d120aec7926ff:GroupProjectApi/src/main/java/com/example/S3_GPS_Ivanti/controller/ResponseController.java
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/response")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ResponseController {

    private final ResponseService responseService;
    private final UserService userService;

    //Creator
    @GetMapping("{reviewID}")
    public ResponseEntity<ArrayList<Response>> getResponse(@PathVariable("reviewID")int reviewID, @RequestBody  String username, @RequestBody  String password) {
        User user = userService.getUser(username, password);

        if(user != null) {
            ArrayList<Response> responses = responseService.getResponse(reviewID);

            if(responses != null) {
                return ResponseEntity.ok().body(responses);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        return new ResponseEntity("Please make sure your username and password are correct", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("{reviewID}")
    public ResponseEntity createResponse(@PathVariable("reviewID")int reviewID, @RequestBody  Response response, @RequestBody  String username, String password) {
        User user = userService.getUser(username, password);

        if(user != null) {
            boolean result = responseService.createResponse(reviewID, response, user);

            if(result == true) {
                return ResponseEntity.ok().build();
            }
            else {
                return new ResponseEntity("Error", HttpStatus.CONFLICT);
            }
        }

        return new ResponseEntity("Please make sure your username and password are correct", HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity updateResponse( @RequestBody Response response, @RequestBody  String username, String password) {
        User user = userService.getUser(username, password);

        if(user != null) {
            responseService.updateResponse(response, user);
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity("Please make sure your username and password are correct", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping({"{appID}"})
    public ResponseEntity deleteResponse( @PathVariable("appID") int responseID, @RequestBody  String username, String password) {
        User user = userService.getUser(username, password);

        if(user != null) {
            boolean result = responseService.deleteResponse(responseID, user);

            if (result == true) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity("Please make sure your username and password are correct", HttpStatus.BAD_REQUEST);
    }
}
