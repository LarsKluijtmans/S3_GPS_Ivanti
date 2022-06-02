package s3_gps_ivanti.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3_gps_ivanti.business.application.*;
import s3_gps_ivanti.configuration.security.isauthenticated.IsAuthenticated;
import s3_gps_ivanti.dto.application.*;
import s3_gps_ivanti.dto.creator.CreatorApplicationListDTO;

import javax.annotation.security.RolesAllowed;
import java.net.URI;


@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class ApplicationController {

    private final CreateApplicationUseCase createApplications;
    private final DeleteApplicationUseCase deleteApplications;
    private final GetApplicationDetailedInfoUseCase getApplicationDetailedInfo;
    private final GetApplicationsBasicInfoUseCase getApplicationsBasicInfo;
    private final UpdateApplicationUseCase updateApplication;
    private final GetApplicationByCreatorUseCase getApplicationByCreator;


    //All
    @GetMapping("/{applicationName}")
    public ResponseEntity<ApplicationDetailedInfoDTO> getApplicationDetails(@PathVariable("applicationName") String applicationName) {

        ApplicationDetailedInfoDTO applicationDetailedInfoDTO = getApplicationDetailedInfo.getApplicationInfo(applicationName);

        if(applicationDetailedInfoDTO == null) {
            return ResponseEntity.notFound().build();
        }

       return ResponseEntity.ok().body(applicationDetailedInfoDTO);
    }

//    @GetMapping
//    public ResponseEntity<List<ApplicationBasicInfoDTO>> getAllApplications(){
//        List<ApplicationBasicInfoDTO> allApplications = getApplicationsBasicInfo.getApplications();
//
//        if (allApplications == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(allApplications);
//    }

    @GetMapping
    public ResponseEntity<GetAllApplicationsResponseDTO> getAllApplications() {
        return ResponseEntity.ok(getApplicationsBasicInfo.getAllApplications());
    }

    //Content Creator

    @PostMapping()
    public ResponseEntity<CreateApplicationResponseDTO> createApplications(@RequestBody CreateApplicationRequestDTO application) {

         CreateApplicationResponseDTO createApplicationResponseDTO = createApplications.createApplications(application);

        if(createApplicationResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else {
            String url = "Application/" + createApplicationResponseDTO.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_Creator"})
    @PutMapping()
    public ResponseEntity<Object> updateApplications(@RequestBody UpdateApplicationRequestDTO application) {
        updateApplication.updateApplications(application);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_Creator"})
    @DeleteMapping({"{applicationID}"})
    public ResponseEntity<Object> deleteApplications(@PathVariable("applicationID") String applicationID) {

        deleteApplications.deleteApplications(applicationID);
        return ResponseEntity.ok().build();
    }

    //TODO fix this

    @GetMapping("/creator/{username}")
    public ResponseEntity<CreatorApplicationListDTO> getApplicationsByCreator(@PathVariable("username") String username) {
        CreatorApplicationListDTO creatorApplicationListDTO = getApplicationByCreator.getApplicationsByCreator(username);
        if (creatorApplicationListDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(creatorApplicationListDTO);
    }
   /*  @GetMapping("creator/{id}")
    public ResponseEntity<List<ApplicationBasicInfoDTO>> getApplicationsByCreator(@PathVariable int id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "sort", required = false) String sort) {
        ArrayList<Application> applications;

        if (StringUtils.hasText(name)) {
            applications = applicationService.getApplicationsByCreatorIdAndName(id, name);
        } else {
            applications = applicationService.getApplicationsByCreatorId(id);
        }

        ArrayList<ApplicationBasicInfoDTO> applicationsDTO  = new ArrayList<>();

        if (!applications.isEmpty()) {
            if (StringUtils.hasText(sort)) {
                switch (sort) {
                    case "nameDesc" -> applicationService.sortApplicationsByName(applications, false);
                    case "ratingAsc" -> applicationService.sortApplicationsByRating(applications, true);
                    case "ratingDesc" -> applicationService.sortApplicationsByRating(applications, false);
                    default -> applicationService.sortApplicationsByName(applications, true);
                }
            } else {
                applicationService.sortApplicationsByName(applications, true);
            }

            for (Application a : applications) {
                ApplicationBasicInfoDTO applicationDTO = new ApplicationBasicInfoDTO(a);
                applicationsDTO.add(applicationDTO);
            }

            return ResponseEntity.ok().body(applicationsDTO);
        }

        return ResponseEntity.notFound().build();
    }*/
}
