package s3_gps_ivanti.business;

import s3_gps_ivanti.dto.*;
import s3_gps_ivanti.model.Application;

import java.io.File;
import java.util.ArrayList;

public interface ApplicationService {

    //All
    ArrayList<Application> getApplications();

    ArrayList<Application> getApplicationsByName(String name);

    void sortApplicationsByName(ArrayList<Application> applications, boolean ascending);

    void sortApplicationsByRating(ArrayList<Application> applications, boolean ascending);

    ApplicationDetailedInfoDTO getApplicationInfoByID(int id);

    ArrayList<Application> getApplicationDetails(String appName);


    //Content Creator
    ArrayList<Application> getApplicationsByCreatorId(int creatorId);

    ArrayList<Application> getApplicationsByCreatorIdAndName(int creatorId, String name);

    CreateApplicationResponseDTO createApplications(CreateApplicationRequestDTO app);

    UpdateApplicationDTO getApplicationToUpdate(String appname);

    boolean updateApplications(UpdateApplicationDTO app);

    boolean deleteApplications(String name);


    //Customer
    ArrayList<Application> getApplicationsByCustomer(int id);

    File downloadApplications(int id);

    ApplicationDetailedInfoDTO getApplicationInfoByName(String name);

    ArrayList<ApplicationStatisticsDTO> getApplicationStatisticsDTO(ArrayList<Application> applications);


    //Versions
    GetVersionDTO getVersion(int applicationId, Double number);
    GetVersionDTO getVersionsByApplication(String appname);
    double createVersion(CreateVersionDTO versionDTO);
    boolean deleteVersion(DeleteVersionDTO versionDTO);
    GetVersionDTO updateVersion(UpdateVersionDTO versionDTO);


}
