package s3_gps_ivanti.controller;


import lombok.RequiredArgsConstructor;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3_gps_ivanti.business.application.*;
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


    @GetMapping
    public ResponseEntity<GetAllApplicationsResponseDTO> getAllApplications(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "sort", required = false) String sort) {
        GetAllApplicationsRequestDTO request = new GetAllApplicationsRequestDTO();
        request.setName(name);
        request.setStatus(false);

        if (StringUtils.hasText(sort)) {
            switch (sort) {
                case "nameAsc" -> {
                    request.setOrderByName(true);
                    request.setAscending(true);
                }
                case "nameDesc" -> {
                    request.setOrderByName(true);
                    request.setAscending(false);
                }
                case "ratingAsc" -> {
                    request.setOrderByName(false);
                    request.setAscending(true);
                }
                case "ratingDesc" -> {
                    request.setOrderByName(false);
                    request.setAscending(false);
                }
            }
        }

        return ResponseEntity.ok(getApplicationsBasicInfo.getAllApplications(request));
    }
}
