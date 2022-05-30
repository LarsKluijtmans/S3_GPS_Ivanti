package s3_gps_ivanti.controller;


import lombok.RequiredArgsConstructor;

import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3_gps_ivanti.business.application.*;
import s3_gps_ivanti.business.version.UpdateVersionUseCase;
import s3_gps_ivanti.configuration.security.isauthenticated.IsAuthenticated;
import s3_gps_ivanti.dto.application.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;


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


    //All
    @GetMapping("/{applicationName}")
    public ResponseEntity<ApplicationDetailedInfoDTO> getApplicationDetails(@PathVariable("applicationName") String applicationName) {

        ApplicationDetailedInfoDTO applicationDetailedInfoDTO = getApplicationDetailedInfo.getApplicationInfo(applicationName);

        if(applicationDetailedInfoDTO == null) {
            return ResponseEntity.notFound().build();
        }

       return ResponseEntity.ok().body(applicationDetailedInfoDTO);
    }
    @GetMapping
    public ResponseEntity<List<ApplicationBasicInfoDTO>> getAllApplications(){
        List<ApplicationBasicInfoDTO> allApplications = getApplicationsBasicInfo.getApplications();

        if (allApplications == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(allApplications);
    }

    //Content Creator
    @IsAuthenticated
    @RolesAllowed({"ROLE_Creator"})
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
    }

     @GetMapping("creator/{id}/statistics")
    public ResponseEntity<List<ApplicationStatisticsDTO>>getApplicationsStatistics(@PathVariable int id) {
        Creator creator = creatorService.getCreator(id);
        List<ApplicationStatisticsDTO> statisticsDTOS = applicationService.getApplicationStatisticsDTO((ArrayList<Application>) creator.getMyApplications());
        if(creator!=null){
            return ResponseEntity.ok().body(statisticsDTOS);
        }
        return ResponseEntity.notFound().build();
    }*/
}
