package s3_gps_ivanti.controller;

import s3_gps_ivanti.DTO.ApplicationBasicInfoDTO;
import s3_gps_ivanti.DTO.ApplicationDetailedInfoDTO;
import s3_gps_ivanti.business.ApplicationService;
import s3_gps_ivanti.model.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ApplicationController {

    private final ApplicationService applicationService;

    //All
    @GetMapping("/filter/{rating}/{date}")
    public ResponseEntity<ArrayList<Application>> getApplicationsSorted(@PathVariable("rating") boolean rating, @PathVariable("date") boolean date ) {

        ArrayList<Application> applications = applicationService.getApplicationsSorted(rating, date);

        if(applications != null) {
            return ResponseEntity.ok().body(applications);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("search/{search}")
    public ResponseEntity<ArrayList<Application>> getApplicationsBySearch(@PathVariable("search") String search) {

        ArrayList<Application> applications = applicationService.getApplicationsBySearch(search);

        if(applications != null) {
            return ResponseEntity.ok().body(applications);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Application> getApplicationsBySearch(@PathVariable("id") long id) {

            Application application = applicationService.getApplicationsByID(id);

            if(application != null) {
                return ResponseEntity.ok().body(application);
            } else {
                return ResponseEntity.notFound().build();
            }
    }
    @GetMapping("/details/{appName}")
    public ResponseEntity<Application> getApplicationDetails(@PathVariable("appName") String appName) {

        Application application = applicationService.getApplicationsByID(2);

        if(application != null) {
            return ResponseEntity.ok().body(application);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<ApplicationBasicInfoDTO>> getAllApplications(){
        ArrayList<Application> allApplications = applicationService.getApplications();
        ArrayList<ApplicationBasicInfoDTO> dtos = new ArrayList<>();

        if (allApplications != null){
            for (Application app : allApplications){
                ApplicationBasicInfoDTO dto = new ApplicationBasicInfoDTO(app);
                dtos.add(dto);
            }
            return ResponseEntity.ok().body(dtos);
        }
        return ResponseEntity.notFound().build();
    }

    //Creator
    @GetMapping("creator/{id}")
    public ResponseEntity<ArrayList<ApplicationBasicInfoDTO>>getApplicationsByCreator(@PathVariable int id) {

        ArrayList<Application> creatorApps = applicationService.getApplicationsByCreator(id);
        ArrayList<ApplicationBasicInfoDTO> dtos = new ArrayList<>();

        // convert to dto
        if (creatorApps != null){
            for (Application app : creatorApps){
                ApplicationBasicInfoDTO dto = new ApplicationBasicInfoDTO(app);
                dtos.add(dto);
            }
            return ResponseEntity.ok().body(dtos);
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Object> createApplications( @RequestBody Application app) {

        if(applicationService.createApplications(app)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PutMapping()
    public ResponseEntity<Object> updateApplications(@RequestBody Application app) {

        if (applicationService.updateApplications(app)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping({"appID"})
    public ResponseEntity<Object> deleteApplications(@PathVariable("appID") int appID) {

        if (applicationService.deleteApplications(appID)) {
            return ResponseEntity.ok().build();
        } else {
               return ResponseEntity.notFound().build();
        }
    }


    //Customer
    @GetMapping("download/{appID}")
    public ResponseEntity<File> downloadApplications(@PathVariable("appID") int appID) {

        File app = applicationService.downloadApplications(appID);

        if (app != null) {
            return ResponseEntity.ok().body(app);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("customer/{ID}")
    public ResponseEntity<ArrayList<Application>>getApplicationsByCustomer(@PathVariable String ID) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
